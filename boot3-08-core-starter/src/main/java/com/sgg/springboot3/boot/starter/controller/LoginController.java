package com.sgg.springboot3.boot.starter.controller;

import com.sgg.springboot3.boot.starter.event.LoginSuccessEvent;
import com.sgg.springboot3.boot.starter.event.PublisherEvent;
import com.sgg.springboot3.boot.starter.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: LoginController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-25 11:04
 * @Version 1.0
 */
@RestController
public class LoginController {

    @Autowired
    private PublisherEvent publisherEvent;

    @Autowired
    private UserProperties userProperties;

    @GetMapping("/login")
//    public String login(@RequestParam("username") String username,
//                        @RequestParam("password") String password){
    public String login(){
        String userName = userProperties.getUserName();
        String password = userProperties.getPassword();

        // 登录事件
        LoginSuccessEvent loginSuccessEvent = new LoginSuccessEvent(new UserProperties(userName, password));
        // 发送事件
        publisherEvent.sendEvent(loginSuccessEvent);

        return userName + "登录成功";
    }
}
