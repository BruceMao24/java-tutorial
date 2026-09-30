package org.example.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * 英文问候实现
 * @Primary：容器里有多个 GreetingService 实现时，优先注入我这个
 */
// @Primary
@Service
public class EnglishGreetingService implements GreetingService {

    @Override
    public String greet(String name) {
        return "Hello, " + name.trim();
    }

    @Override
    public String greetUser(String name, Integer age) {
        return "Hello, " + name.trim() + ", age " + age;
    }
}
