package com.sgg.springboot3.boot.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

/**
 * ClassName: Log
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-02 13:03
 * @Version 1.0
 */

@Aspect
@Component
public class CarAspect {


    @Before("execution(* com.sgg.springboot3.boot.service..*.*(..))")
    public void carLog(JoinPoint jp){
        // StringJoiner(分隔符， 前包围， 后包围)
        StringJoiner joiner = new StringJoiner("|", "[", "]");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        Date date = new Date();
        String now = sdf.format(date);
        joiner.add(now);

        String methodName = jp.getSignature().getName();
        joiner.add(methodName);
        Object[] args = jp.getArgs();
        for (Object arg: args){
            joiner.add(arg == null ? "-" : arg.toString());
        }

        System.out.println("日志 = " + joiner);
    }

}
