package org.example.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类:生成和解析 token
 */
@Component
public class JwtUtil {

    /** 密钥(从配置文件读取,至少 256 位) */
    @Value("${jwt.secret:my-secret-key-for-jwt-token-generation-must-be-long-enough}")
    private String secret;

    /** 过期时间(毫秒),默认 24 小时 */
    @Value("${jwt.expiration:86400000}")
    private Long expiration;

    /**
     * 生成 JWT token
     * @param userId 用户 ID
     * @param username 用户名
     * @param role 角色
     * @return token 字符串
     */
    public String generateToken(Long userId, String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);
        claims.put("role", role);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    /**
     * 解析 token,获取 Claims
     * @param token JWT token
     * @return Claims 对象,解析失败返回 null
     */
    public Claims parseToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从 token 中获取用户 ID
     */
    public Long getUserId(String token) {
        Claims claims = parseToken(token);
        return claims != null ? claims.get("userId", Long.class) : null;
    }

    /**
     * 从 token 中获取用户名
     */
    public String getUsername(String token) {
        Claims claims = parseToken(token);
        return claims != null ? claims.get("username", String.class) : null;
    }

    /**
     * 验证 token 是否有效
     */
    public boolean validateToken(String token) {
        return parseToken(token) != null;
    }
}
