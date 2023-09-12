package com.example.boot.blog.settings;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: MvcSettings
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-09 14:36
 * @Version 1.0
 */
@Configuration
public class MvcSettings implements WebMvcConfigurer {

    // /view/addArticle 没什么逻辑处理，只是简单跳转，所以使用视图控制器来完成就好，不用专门写一个controller
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/view/addArticle").setViewName("/blog/addArticle");
    }
}
