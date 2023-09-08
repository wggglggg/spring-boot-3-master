package com.sgg.springboot3.boot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: LoginServlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-06 15:40
 * @Version 1.0
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();
        System.out.println("这是一个Login的Servlet");
        out.write("这是一个Login的Servlet, 并且使用代码方式来注册");
        out.flush();
        out.close();
    }
}
