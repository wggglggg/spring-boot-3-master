package com.sgg.springboot3.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: HelloController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-30 17:29
 * @Version 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public String hello(){

        return "Hello, Spring Boot AOT!!!";
    }
}
