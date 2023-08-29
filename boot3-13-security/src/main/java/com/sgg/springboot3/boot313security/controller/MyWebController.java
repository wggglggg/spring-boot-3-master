package com.sgg.springboot3.boot313security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: MyWebControlller
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-29 9:24
 * @Version 1.0
 */
@RestController
public class MyWebController {

//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }



    @GetMapping("/hello")
    public String hello(){
        return "Hello!Spring Security";
    }

    @PreAuthorize("hasAuthority('exec')")   // 只能zhaixuan能访问此页面
    @GetMapping("/world")
    public String world(){
        return "Hello World!!!";
    }



}
