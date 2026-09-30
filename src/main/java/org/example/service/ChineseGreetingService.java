package org.example.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * 中文问候实现
 */
@Primary
@Service
public class ChineseGreetingService implements GreetingService {

    @Override
    public String greet(String name) {
        return "你好, " + name.trim();
    }

    @Override
    public String greetUser(String name, Integer age) {
        return "你好, " + name.trim() + "，年龄 " + age;
    }
}
