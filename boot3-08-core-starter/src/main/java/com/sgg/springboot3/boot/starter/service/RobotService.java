package com.sgg.springboot3.boot.starter.service;

import com.sgg.springboot3.boot.starter.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: RobotService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-25 11:55
 * @Version 1.0
 */
@Service
public class RobotService {

    @Autowired
    private UserProperties userProperties;

    public String sayHello(){
        return "你好：名字：【"+userProperties.getUserName()+"】;年龄：【"+userProperties.getPassword()+"】";
    }
}
