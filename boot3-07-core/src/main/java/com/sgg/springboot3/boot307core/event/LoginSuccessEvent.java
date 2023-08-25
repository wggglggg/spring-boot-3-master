package com.sgg.springboot3.boot307core.event;

import com.sgg.springboot3.boot307core.bean.User;
import org.springframework.context.ApplicationEvent;

/**
 * ClassName: LoginSuccessEvent
 * Description:
 *  登录成功事件。所有事件都推荐继承 ApplicationEvent
 * @Author wggglggg
 * @Create 2023-08-24 14:55
 * @Version 1.0
 */
public class LoginSuccessEvent extends ApplicationEvent {

    /**
     *
     * @param source  代表是谁登录成了
     */
    public LoginSuccessEvent(Object source) {
        super(source);
    }
}
