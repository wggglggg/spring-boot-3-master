package com.sgg.springboot3.boot.controller;


import com.sgg.springboot3.boot.bean.User;
import com.sgg.springboot3.boot.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: HelloController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/8/14 10:09
 * @Version 1.0
 */
@RestController
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/hello")
    public String hello(){

        return "hello boot3 and world";
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Integer id){

        return userMapper.getUserById(id);
    }

}
