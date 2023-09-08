package com.sgg.springboot3.boot.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName: LoginInterceptor
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-07 10:30
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    private List<String> permitUser = null;

    public LoginInterceptor() {
        // 初始化时就给列表赋值
        permitUser = Arrays.asList("admin", "root", "wggglggg");
    }

    //过滤器
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        System.out.println("uri = " + uri);
        //获取登录的用户名称
        String loginUser = request.getParameter("loginUser");
        //验证用户是否是列表中指定的之一，存在于列表中return true, 不属于列表中任一一个false
        System.out.println("==========LoginInterceptor执行了=========="+ loginUser);
        if (StringUtils.hasText(loginUser) && permitUser.contains(loginUser)){
            return true;
        }
        System.out.println( loginUser + "==========此账号没有权限进行此操作==========");
        return false;
    }
}
