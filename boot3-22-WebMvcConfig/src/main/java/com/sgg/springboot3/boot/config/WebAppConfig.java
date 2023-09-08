package com.sgg.springboot3.boot.config;

import com.sgg.springboot3.boot.formatter.PositionFormatter;
import com.sgg.springboot3.boot.interceptor.LoginInterceptor;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebAppConfig
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-06 19:54
 * @Version 1.0
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer{


    //页面跳转控制器， 从请求直达视图页面（无需controller） 只要controller没什么逻辑代码，只是跳转，可以写这里
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //配置页面控制：  addViewController("请求uri")， 指定他的视图setViewName(目标视图)
        registry.addViewController("/welcome").setViewName("index");
    }


    //注册自定义转换器,不然springboot不知道你自定义Formatter的存在
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new PositionFormatter());
    }

    //注册自定义的拦截器Interceptor


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**") //拦截所有对controller的请求
                .excludePathPatterns("/article/query"); //排除
    }
}
