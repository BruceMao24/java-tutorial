package org.example.controller;

import org.example.service.GreetingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 控制层：只负责接收请求、调用 Service、返回响应，不写业务逻辑
 * 注意：依赖的是接口 GreetingService，而不是任何具体实现类
 */
@RestController
public class HelloController {

    private final GreetingService greetingService;

    /**
     * 读取配置文件里的自定义配置 app.company
     */
    @Value("${app.company}")
    private String company;

    /**
     * 构造器注入：Spring 容器自动把 GreetingService 的实现传进来
     */
    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return Map.of(
                "code", 0,
                "message", "Hello, Spring Boot!",
                "data", Map.of("name", "java-tutorial", "company", company)
        );
    }

    /**
     * 路径参数：GET /hello/path/zhangsan
     */
    @GetMapping("/hello/path/{name}")
    public Map<String, Object> helloPath(@PathVariable String name) {
        return Map.of("code", 0, "message", greetingService.greet(name), "from", "@PathVariable");
    }

    /**
     * 查询参数：GET /hello/query?name=zhangsan
     * defaultValue 让 name 可省略：GET /hello/query
     */
    @GetMapping("/hello/query")
    public Map<String, Object> helloQuery(@RequestParam(defaultValue = "world") String name) {
        return Map.of("code", 0, "message", greetingService.greet(name), "from", "@RequestParam");
    }

    /**
     * 请求体：POST /hello/body  Body: {"name": "zhangsan", "age": 18}
     * Spring 自动把 JSON 反序列化成 User 对象
     */
    @PostMapping("/hello/body")
    public Map<String, Object> helloBody(@RequestBody User user) {
        return Map.of("code", 0, "message", greetingService.greetUser(user.name(), user.age()), "from", "@RequestBody");
    }

    /**
     * 请求体对应的 Java 类型（record 是不可变数据类的简洁写法）
     */
    public record User(String name, Integer age) {
    }
}
