package com.sgg.springboot3.boot.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: GlobalExceptionHandler
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-18 21:53
 * @Version 1.0
 */
//@ControllerAdvice  //这个类是集中处理所有 @Controller 发生的错误
public class GlobalExceptionHandler {
    /**
     * 1、@ExceptionHandler 标识一个方法处理错误，默认只能处理这个类发生的指定错误
     * 2、@ControllerAdvice 统一处理所有错误
     * @param e
     * @return
     */
//    @ResponseBody
//    @ExceptionHandler(Exception.class)
    public String handleException(Exception e){
        System.out.println("e = " + e.getMessage());

        return "所有错误统一处理" + e.getMessage();
    }
}
