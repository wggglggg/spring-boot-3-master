package com.sgg.springboot3.boot.starter.event;

import org.springframework.context.ApplicationEvent;

/**
 * ClassName: LoginSuccessEvent
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-25 11:06
 * @Version 1.0
 */
public class LoginSuccessEvent extends ApplicationEvent {
    public LoginSuccessEvent(Object source) {
        super(source);
    }
}
