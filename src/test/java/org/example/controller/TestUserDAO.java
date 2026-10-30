package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TestUserDAO {

    @Autowired
    private UserMapper userMapper;

    // 查询所有
    @Test
    public void testFindAll() {
        List<User> users = userMapper.selectList(null);
        users.forEach(user -> System.out.println("user = " + user));
    }

    // 条件查询
    @Test
    public void testFindList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role", "TEST");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> System.out.println("user" + user));
    }

    // 模糊查询
    @Test
    public void testFindLike() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("role", "T");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> System.out.println("user + " + user));
    }

}
