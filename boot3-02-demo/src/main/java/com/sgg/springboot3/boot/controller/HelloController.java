package com.sgg.springboot3.boot.controller;

import com.sgg.springboot3.boot.bean.Hanmeimei;
import com.sgg.springboot3.boot.bean.Lilei;
import com.sgg.springboot3.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/hello")
    public String hello(){

        return "hello boot3 and world";
    }

}
