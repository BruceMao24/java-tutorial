package org.example.service.impl;

import org.example.service.GreetingService;

import org.example.service.GreetingService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * 英文问候实现
 * 仅当 greeting.language=en 时才创建这个 Bean
 */
@Service
@ConditionalOnProperty(name = "greeting.language", havingValue = "en")
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
