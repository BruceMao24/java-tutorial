package org.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI(Swagger)配置
 * 访问地址: http://localhost:8080/swagger-ui/index.html
 * API 文档 JSON: http://localhost:8080/v3/api-docs
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("图书管理系统 API")
                        .version("1.0.0")
                        .description("基于 Spring Boot + MyBatis-Plus 的图书管理系统")
                        .contact(new Contact()
                                .name("毛坤")
                                .email("admin@example.com")))
                // 配置 JWT 认证
                .components(new Components()
                        .addSecuritySchemes("bearer-jwt", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("输入 JWT token,不需要加 'Bearer ' 前缀")))
                .addSecurityItem(new SecurityRequirement().addList("bearer-jwt"));
    }
}
