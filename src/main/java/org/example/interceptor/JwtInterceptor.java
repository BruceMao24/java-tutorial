package org.example.interceptor;

import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.ErrorCode;
import org.example.common.Result;
import org.example.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JWT 拦截器:验证请求头中的 token
 * 除了登录、注册等白名单接口,其他都需要 token
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public JwtInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 从请求头获取 token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // 去掉 "Bearer " 前缀
        }

        // 2. 验证 token
        if (token == null || !jwtUtil.validateToken(token)) {
            // token 无效,返回 401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            Result<Void> result = Result.error(ErrorCode.UNAUTHORIZED);
            response.getWriter().write(JSONUtil.toJsonStr(result));
            return false;  // 拦截请求
        }

        // 3. token 有效,解析用户信息,存入 request attribute 供 Controller 使用
        Claims claims = jwtUtil.parseToken(token);
        request.setAttribute("userId", claims.get("userId", Long.class));
        request.setAttribute("username", claims.get("username", String.class));
        request.setAttribute("role", claims.get("role", String.class));

        return true;  // 放行
    }
}
