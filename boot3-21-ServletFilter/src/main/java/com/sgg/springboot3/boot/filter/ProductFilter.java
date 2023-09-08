package com.sgg.springboot3.boot.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: YuthFilter
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-06 16:00
 * @Version 1.0
 */
//@WebFilter("/*")
public class ProductFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();

        System.out.println("=========ProductFilter 过滤器执行了，uri："+uri);

        chain.doFilter(request, response);
    }
}
