package com.sgg.springboot3.boot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: NumberController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-07 17:03
 * @Version 1.0
 */
@RestController
public class NumberController {

    @PostMapping("/divide")
    public String sum(Integer n1, Integer n2){
        int result = n1 / n2;
        return "n1 / n2 = " + result;
    }
}
