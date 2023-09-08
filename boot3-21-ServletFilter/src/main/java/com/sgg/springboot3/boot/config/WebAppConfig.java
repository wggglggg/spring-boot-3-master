package com.sgg.springboot3.boot.config;

import com.sgg.springboot3.boot.filter.LoginFilter;
import com.sgg.springboot3.boot.filter.ProductFilter;
import com.sgg.springboot3.boot.servlet.LoginServlet;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: WebAppConfig
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-06 15:44
 * @Version 1.0
 */
@Configuration
public class WebAppConfig {

    // 采用代码方式，不写注解@WebServlet(urlPatterns = "/simple/login", name = "LoginServlet")
    @Bean
    public ServletRegistrationBean registrationBean(){
        ServletRegistrationBean loginServlet = new ServletRegistrationBean();
        loginServlet.setServlet(new LoginServlet());
        loginServlet.addUrlMappings("/user/login");
        loginServlet.setLoadOnStartup(1);
        return loginServlet;
    }

    // 也可以用这种代码方式 不写注解@WebFilter(urlPatterns = "/*")
    @Bean
    public FilterRegistrationBean addLoginFilter(){
        FilterRegistrationBean<Filter> loginFilter = new FilterRegistrationBean<>();
        loginFilter.setFilter(new LoginFilter());
        loginFilter.addUrlPatterns("/*");
        System.out.println("addLoginFilter in WebAppConfig");
        loginFilter.setOrder(1);
        return loginFilter;
    }

    /**
     * 2023-09-06T16:08:02.535+08:00  INFO 14880 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 496 ms
     * addLoginFilter in WebAppConfig
     * addProductFilter in WebAppConfig
     * 2023-09-06T16:08:02.715+08:00  INFO 14880 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
     * 2023-09-06T16:08:02.720+08:00  INFO 14880 --- [
     * @return
     */
    @Bean
    public FilterRegistrationBean addProductFilter(){
        FilterRegistrationBean productFilter = new FilterRegistrationBean();
        productFilter.setFilter(new ProductFilter());
        productFilter.addUrlPatterns("/*");
        System.out.println("addProductFilter in WebAppConfig");
        productFilter.setOrder(2);

        return productFilter;
    }
}
