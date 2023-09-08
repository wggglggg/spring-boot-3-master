package com.sgg.springboot3.boot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: HelloServlet
 * Description:
 *      自定义Servlet,可以修改根路径
 * @WebServlet: 等同于web.xml中 有关Servlet的声明
 *
 * <servlet>
 *   <servlet-name>HelloServlet</servlet-name>
 *   <servlet-class>xxxx</servlet-class>
 * </servlet>
 * <servlet-mapping>
 *   <url-pattern>/helloServlet</url-pattern>
 * </servlet-mapping>
 *
 * @Author wggglggg
 * @Create 2023-09-06 15:31
 * @Version 1.0
 */
@WebServlet(urlPatterns = "/simple/server", name = "HelloServlet")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();
        out.write("这是HelloServlet在springboot中");
        out.flush();
        out.close();
    }
}
