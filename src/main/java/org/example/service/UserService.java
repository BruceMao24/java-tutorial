package org.example.service;

import org.example.entity.User;

/**
 * 用户业务接口
 */
public interface UserService {

    /**
     * 用户注册
     * @param username 用户名
     * @param password 明文密码
     * @param email 邮箱
     * @return 注册成功的用户 ID
     */
    Long register(String username, String password, String email);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 明文密码
     * @return JWT token,登录失败返回 null
     */
    String login(String username, String password);

    /**
     * 根据用户名查询用户
     */
    User getByUsername(String username);

    /**
     * 根据 ID 查询用户
     */
    User getById(Long id);
}
