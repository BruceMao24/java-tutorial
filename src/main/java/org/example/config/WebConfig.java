package org.example.config;

import org.example.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC 配置:注册拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final JwtInterceptor jwtInterceptor;

    public WebConfig(JwtInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")  // 拦截所有请求
                .excludePathPatterns(
                        "/users/register",      // 注册
                        "/users/login",         // 登录
                        "/error",               // Spring 默认错误页
                        "/swagger-ui/**",       // Swagger UI 页面
                        "/v3/api-docs/**",      // OpenAPI JSON 文档
                        "/swagger-resources/**" // Swagger 资源
                );
    }
}
