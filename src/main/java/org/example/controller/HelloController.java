package org.example.controller;

import org.example.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 控制层：只负责接收请求、调用 Service、返回响应，不写业务逻辑
 */
@RestController
public class HelloController {

    private final HelloService helloService;

    /**
     * 构造器注入：Spring 容器自动把 HelloService 的实例传进来
     */
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return Map.of(
                "code", 0,
                "message", "Hello, Spring Boot!",
                "data", helloService.appInfo()
        );
    }

    /**
     * 路径参数：GET /hello/path/zhangsan
     */
    @GetMapping("/hello/path/{name}")
    public Map<String, Object> helloPath(@PathVariable String name) {
        return Map.of("code", 0, "message", helloService.greet(name), "from", "@PathVariable");
    }

    /**
     * 查询参数：GET /hello/query?name=zhangsan
     * defaultValue 让 name 可省略：GET /hello/query
     */
    @GetMapping("/hello/query")
    public Map<String, Object> helloQuery(@RequestParam(defaultValue = "world") String name) {
        return Map.of("code", 0, "message", helloService.greet(name), "from", "@RequestParam");
    }

    /**
     * 请求体：POST /hello/body  Body: {"name": "zhangsan", "age": 18}
     * Spring 自动把 JSON 反序列化成 User 对象
     */
    @PostMapping("/hello/body")
    public Map<String, Object> helloBody(@RequestBody User user) {
        return Map.of("code", 0, "message", helloService.greetUser(user.name(), user.age()), "from", "@RequestBody");
    }

    /**
     * 请求体对应的 Java 类型（record 是不可变数据类的简洁写法）
     */
    public record User(String name, Integer age) {
    }
}
