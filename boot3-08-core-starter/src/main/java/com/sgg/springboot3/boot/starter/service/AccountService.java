package com.sgg.springboot3.boot.starter.service;

import com.sgg.springboot3.boot.starter.event.LoginSuccessEvent;
import com.sgg.springboot3.boot.starter.properties.UserProperties;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * ClassName: AccountService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-25 11:28
 * @Version 1.0
 */
@Service
public class AccountService {

    public void addAccountScore(String username){
        System.out.println(username +" 加了1分");
    }

    @EventListener
    public void onEvent(LoginSuccessEvent loginSuccessEvent){
        System.out.println("=====  AccountService  感应到事件 =====" + loginSuccessEvent);
        UserProperties userProperties = (UserProperties) loginSuccessEvent.getSource();

        addAccountScore(userProperties.getUserName());
    }
}
