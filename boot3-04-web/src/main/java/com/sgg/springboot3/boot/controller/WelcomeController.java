package com.sgg.springboot3.boot.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

/**
 * ClassName: WelcomeController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-17 18:02
 * @Version 1.0
 */
@Controller //适配 服务端渲染   前后不分离模式开始, RestController是前后端分离
public class WelcomeController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Locale locale = request.getLocale();
        System.out.println("locale = " + locale);

//        int i = 10 / 0;

        return "index";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

}
