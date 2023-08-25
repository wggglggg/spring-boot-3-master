package com.sgg.springboot3.boot307core.event;

import com.sgg.springboot3.boot307core.bean.User;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * ClassName: SystemService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-24 15:42
 * @Version 1.0
 */
@Service
public class SystemService {

    public void recordeLog(String username){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        long l = System.currentTimeMillis();
        String format = sdf.format(l);

        System.out.println(username + "登录信息已被记录" + format);
    }
    @EventListener
    public void onEvent(LoginSuccessEvent loginSuccessEvent){
        Object source = loginSuccessEvent.getSource();
        User user = (User) source;

        recordeLog(user.getUserName());
    }
}
