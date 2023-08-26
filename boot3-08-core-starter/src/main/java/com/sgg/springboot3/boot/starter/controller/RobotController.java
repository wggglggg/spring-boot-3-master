package com.sgg.springboot3.boot.starter.controller;

import com.sgg.springboot3.boot.starter.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: RobotController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-25 11:54
 * @Version 1.0
 */
@RestController
public class RobotController {

    @Autowired
    private RobotService robotService;

    @GetMapping("/robot/hello")
    public String sayHello(){
        String s = robotService.sayHello();

        return s;
    }
}
