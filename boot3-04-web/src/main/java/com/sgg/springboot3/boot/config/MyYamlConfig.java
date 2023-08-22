package com.sgg.springboot3.boot.config;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * ClassName: MyYamlConfig
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-17 9:03
 * @Version 1.0
 */
public class MyYamlConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(null);
    }
}
