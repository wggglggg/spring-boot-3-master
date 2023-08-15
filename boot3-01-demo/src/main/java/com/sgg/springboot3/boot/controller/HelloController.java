package com.sgg.springboot3.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: HelloController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/8/13 19:53
 * @Version 1.0
 */
@RestController
public class HelloController {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/hello")
    public String hello(){

        return "Hello World and SpringBoot3";
    }

    @GetMapping("/increment")
    public String increament(){
        Long autoIncrement = redisTemplate.opsForValue().increment("Autoincrement");
        return "增加后的值 " + autoIncrement;
    }


}
