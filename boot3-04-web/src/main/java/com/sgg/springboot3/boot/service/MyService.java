package com.sgg.springboot3.boot.service;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;


/**
 * ClassName: MyService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-22 15:09
 * @Version 1.0
 */
@Service
public class MyService {

    @Test
    public void testRequest(HttpServletRequest request){
        //当前请求的路径
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);
    }


}
