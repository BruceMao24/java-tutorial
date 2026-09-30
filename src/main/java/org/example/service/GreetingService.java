package org.example.service;

/**
 * 问候服务接口：只定义"能做什么"，不关心"怎么做"
 */
public interface GreetingService {

    String greet(String name);

    String greetUser(String name, Integer age);
}
