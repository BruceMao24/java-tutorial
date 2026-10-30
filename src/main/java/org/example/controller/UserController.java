package org.example.controller;

import org.example.common.ErrorCode;
import org.example.common.Result;
import org.example.dto.LoginResponse;
import org.example.dto.PasswordChangeDTO;
import org.example.dto.UserLoginDTO;
import org.example.dto.UserProfileResponse;
import org.example.dto.UserRegisterDTO;
import org.example.entity.User;
import org.example.exception.BusinessException;
import org.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口:注册、登录、个人信息
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册:POST /users/register
     * Body: {"username":"zhangsan", "password":"123456", "confirmPassword":"123456", "email":"zhangsan@example.com"}
     */
    @PostMapping("/register")
    public Result<Long> register(@Validated @RequestBody UserRegisterDTO dto) {
        // 校验两次密码是否一致
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }

        Long userId = userService.register(dto.getUsername(), dto.getPassword(), dto.getEmail());
        log.info("用户注册成功: {}, id={}", dto.getUsername(), userId);
        return Result.ok(userId);
    }

    /**
     * 登录:POST /users/login
     * Body: {"username":"zhangsan", "password":"123456"}
     */
    @PostMapping("/login")
    public Result<LoginResponse> login(@Validated @RequestBody UserLoginDTO dto) {
        String token = userService.login(dto.getUsername(), dto.getPassword());

        User user = userService.getByUsername(dto.getUsername());
        LoginResponse response = new LoginResponse(
                token,
                user.getId(),
                user.getUsername(),
                user.getRole()
        );

        log.info("用户登录成功: {}", dto.getUsername());
        return Result.ok(response);
    }

    /**
     * 获取当前登录用户信息(需要 JWT token)
     * 请求头: Authorization: Bearer <token>
     */
    @GetMapping("/profile")
    public Result<UserProfileResponse> profile(HttpServletRequest request) {
        // 从拦截器中设置的 request attribute 获取用户信息
        Long userId = (Long) request.getAttribute("userId");

        User user = userService.getById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }

        UserProfileResponse response = new UserProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getEmail(),
                user.getCreateTime()
        );

        return Result.ok(response);
    }

    @PutMapping("/password")
    public Result<Void> password(@Validated @RequestBody PasswordChangeDTO dto, HttpServletRequest request) {
        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }

        Long userId = (Long) request.getAttribute("userId");
        userService.changePassword(userId, dto.getOldPassword(), dto.getNewPassword());
        return Result.ok();
    }
}
