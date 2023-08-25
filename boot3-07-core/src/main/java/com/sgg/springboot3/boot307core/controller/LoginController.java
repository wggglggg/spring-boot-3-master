package com.sgg.springboot3.boot307core.controller;

import com.sgg.springboot3.boot307core.bean.User;
import com.sgg.springboot3.boot307core.event.EventPublisher;
import com.sgg.springboot3.boot307core.event.LoginSuccessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: LoginController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-24 14:52
 * @Version 1.0
 */
@RestController
public class LoginController {

    @Autowired
    EventPublisher eventPublisher;

    /**
     * 增加业务
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("username") String password){

        //业务处理登录
        System.out.println("业务处理登录完成....");

        //1、创建事件信息
        LoginSuccessEvent loginSuccessEvent = new LoginSuccessEvent(new User(username, password));
        System.out.println("loginSuccessEvent完成....");
        //2、发送事件
        eventPublisher.sendEvent(loginSuccessEvent);
        return "登录成功";
    }
}
