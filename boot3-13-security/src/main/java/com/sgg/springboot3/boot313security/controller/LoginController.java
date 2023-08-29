package com.sgg.springboot3.boot313security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * ClassName: LoginController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-29 10:37
 * @Version 1.0
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

}
