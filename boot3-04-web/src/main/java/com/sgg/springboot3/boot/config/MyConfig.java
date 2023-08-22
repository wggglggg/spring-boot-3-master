package com.sgg.springboot3.boot.config;

//import com.sgg.springboot3.boot.component.MyYamlHttpMessageConverter;
//import com.sgg.springboot3.boot.component.MyYamlHttpMessageConverterByExtend;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: MyConfig
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-16 8:58
 * @Version 1.0
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

    // 方式一
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //保留以前规则 注释也没关系,原来的还是会保留
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//
//        //自己写新的规则。
//        registry
//                .addResourceHandler("/welcome/**")
//                .addResourceLocations("classpath:/my_path_pattern/")
//                .setCacheControl(CacheControl.maxAge(2, TimeUnit.SECONDS))
//                .setUseLastModified(true);
//    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new MyYamlHttpMessageConverterByExtend());
//    }



    // 方式二
//    @Bean
//    public WebMvcConfigurer webMvcConfigurer(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//                registry.addResourceHandler("/welcomeyou/**")
//                        .addResourceLocations("classpath:/my_path_pattern/");
//            }
//        };
//    }
    // 方式三
//    @Bean
//    public WebMvcConfigurer webMvcConfigurer(){
//
//        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
//            @Override
//            public void addResourceHandlers(ResourceHandlerRegistry registry) {
//                registry.addResourceHandler("/welcome/**").addResourceLocations("classpath:/my_path_pattern/")
//                        .setCacheControl(CacheControl.maxAge(5, TimeUnit.SECONDS))
//                        .setUseLastModified(true);
//            }
//        };
//        return webMvcConfigurer;
//    }
}
