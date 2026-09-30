package org.example.service.impl;

import org.example.service.GreetingService;

import org.example.service.GreetingService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * 中文问候实现
 * 仅当 greeting.language=zh（或未配置）时才创建这个 Bean
 */
@Service
@ConditionalOnProperty(name = "greeting.language", havingValue = "zh", matchIfMissing = true)
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
