package org.example.dto;

import java.time.LocalDateTime;

/**
 * 用户信息响应 DTO
 */
public class UserProfileResponse {

    private Long userId;
    private String username;
    private String role;
    private String email;
    private LocalDateTime createTime;

    public UserProfileResponse() {
    }

    public UserProfileResponse(Long userId, String username, String role, String email, LocalDateTime createTime) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.email = email;
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
