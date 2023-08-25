package com.sgg.springboot3.boot307core.service;

import com.sgg.springboot3.boot307core.bean.User;
import com.sgg.springboot3.boot307core.event.LoginSuccessEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * ClassName: AccountService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-24 14:48
 * @Version 1.0
 */
@Service
public class AccountService  {

    public void addAccountScore(String  username){
        System.out.println(username +" 加了1分");
    }

    @EventListener
    public void onEvent(LoginSuccessEvent loginSuccessEvent){
        System.out.println("===== AccountService ====感知到事件"+loginSuccessEvent);
        Object source = loginSuccessEvent.getSource();
        User user = (User) source;


        addAccountScore(user.getUserName());
    }
}
