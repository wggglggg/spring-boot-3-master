package com.sgg.springboot3.boot.controller;

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

    @GetMapping("/hello")
    public String hello(){

        return "Hello World and SpringBoot3";
    }

}
