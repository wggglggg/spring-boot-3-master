package com.sgg.springboot3.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: HelloController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-15 15:30
 * @Version 1.0
 */
@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        for (int i = 0; i < 1000; i++) {
            log.trace("trace 日志.....");
            log.debug("debug 日志.....");
            //SpringBoot底层默认的日志级别 info
            log.info("info 日志..... ");
            log.warn("warn 日志...");
            log.error("error 日志...");
        }

        return "hello";
    }
}
