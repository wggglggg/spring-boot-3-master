package com.sgg.springboot3.boot.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * ClassName: LoginFilter
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-05 8:40
 * @Version 1.0
 */

// @WebFilter(urlPatterns = "/*")  // @ServletComponentScan(basePackages = "com.sgg.springboot3.boot") 还需要设置扫描包路径
public class LoginFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        request.setCharacterEncoding("utf-8");
        String uri = ((HttpServletRequest) request).getRequestURI();

        System.out.println("=========LogFilter 过滤器执行了，uri："+uri);
        chain.doFilter(request, response);
    }
}
