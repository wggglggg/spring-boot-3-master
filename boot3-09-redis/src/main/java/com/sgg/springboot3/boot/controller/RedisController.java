package com.sgg.springboot3.boot.controller;

import com.sgg.springboot3.boot.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * ClassName: RedisController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-26 15:56
 * @Version 1.0
 */
@RestController
public class RedisController {

    @Autowired  // 只处理类型String 无法处理对象例如Person
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @GetMapping("/count")
    public String count(){
        Long score = stringRedisTemplate.opsForValue().increment("score");

        //常见数据类型  k: v value可以有很多类型
        //string： 普通字符串 ： stringRedisTemplate.opsForValue()
        //list:    列表：       stringRedisTemplate.opsForList()
        //set:     集合:       stringRedisTemplate.opsForSet()
        //zset:    有序集合:    stringRedisTemplate.opsForZSet()
        //hash：   map结构：    stringRedisTemplate.opsForHash()

        return "访问count" + score +  " 次数";
    }

    @GetMapping("/person/save")
    public String savePerson(@RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("age") Integer age){
        Date date = new Date();

        Person person = new Person(id, name, age,date);
        System.out.println("new Date() = " + date);

        redisTemplate.opsForValue().set("person", person);

        return "ok";
    }

    @GetMapping("/person/get")
    public Person getPerson(){
        Person person = (Person) redisTemplate.opsForValue().get("person");

        return person;
    }
}
