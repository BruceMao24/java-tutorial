package org.example.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 业务逻辑层：问候相关的业务规则都在这里
 */
@Service
public class HelloService {

    public Map<String, Object> appInfo() {
        return Map.of("name", "java-tutorial", "port", 8080);
    }

    /**
     * 业务规则：生成问候语（示例：中文问候 + 去掉名字两端空格）
     */
    public String greet(String name) {
        return "你好, " + name.trim();
    }

    public String greetUser(String name, Integer age) {
        return "你好, " + name.trim() + "，年龄 " + age;
    }
}
