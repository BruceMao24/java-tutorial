package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.common.ErrorCode;
import org.example.entity.User;
import org.example.exception.BusinessException;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.example.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserMapper userMapper, JwtUtil jwtUtil) {
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Long register(String username, String password, String email) {
        // 1. 检查用户名是否已存在
        User existing = getByUsername(username);
        if (existing != null) {
            throw new BusinessException(ErrorCode.USERNAME_EXISTS);
        }

        // 2. 密码 BCrypt 加密
        String passwordHash = passwordEncoder.encode(password);

        // 3. 创建用户
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordHash);
        user.setEmail(email);
        user.setRole("USER");  // 默认普通用户

        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public String login(String username, String password) {
        // 1. 查询用户
        User user = getByUsername(username);
        if (user == null) {
            throw new BusinessException(ErrorCode.LOGIN_FAILED);
        }

        // 2. 验证密码
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new BusinessException(ErrorCode.LOGIN_FAILED);
        }

        // 3. 生成 JWT token
        return jwtUtil.generateToken(user.getId(), user.getUsername(), user.getRole());
    }

    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        // 1. 先查询当前用户
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }

        // 2. 验证旧密码是否正确
        if (!passwordEncoder.matches(oldPassword, user.getPasswordHash())) {
            throw new BusinessException(ErrorCode.PASSWORD_INCORRECT);
        }

        // 3. 加密新密码并更新
        String newPasswordHash = passwordEncoder.encode(newPassword);
        user.setPasswordHash(newPasswordHash);
        userMapper.updateById(user);
    }
}
