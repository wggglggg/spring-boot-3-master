package com.sgg.springboot3.boot307core.service;

import com.sgg.springboot3.boot307core.bean.User;
import com.sgg.springboot3.boot307core.event.LoginSuccessEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * ClassName: CuponService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-24 15:24
 * @Version 1.0
 */
@Service
public class CouponService {

    public void sendCoupon(String username){
        System.out.println(username + " 随机得到了一张优惠券");
    }

    @EventListener
    public void onEvent(LoginSuccessEvent loginSuccessEvent){
        System.out.println("===== CouponService ====感知到事件"+loginSuccessEvent);
        Object source = loginSuccessEvent.getSource();
        User user = (User) source;

        sendCoupon(user.getUserName());
    }
}
