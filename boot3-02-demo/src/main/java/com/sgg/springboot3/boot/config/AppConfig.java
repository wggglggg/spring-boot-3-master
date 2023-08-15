package com.sgg.springboot3.boot.config;

import com.alibaba.druid.FastsqlException;
import com.sgg.springboot3.boot.bean.*;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * ClassName: AppConfig
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/8/14 10:51
 * @Version 1.0
 */
//@Import(FastsqlException.class)
@EnableConfigurationProperties(Car.class)
//导入第三方写好的组件进行属性绑定
//SpringBoot默认只扫描自己主程序所在的包。如果导入第三方包，即使组件上标注了 @Component、@ConfigurationProperties 注解，也没用。因为组件都扫描不进来
//@ConditionalOnMissingClass(value="com.alibaba.druid.FastsqlException")

// 放在类级别，如果注解判断生效，则整个配置类才生效
@SpringBootConfiguration    //这是一个配置类，替代以前的配置文件。配置类本身也是容器中的组件
//@Configuration  //这是一个配置类，替代以前的配置文件。
public class AppConfig {

    @Bean("chuliuxiang")//替代以前的Bean标签。组件在容器中的名字默认是方法名，可以直接修改注解的值
//    @Scope("prototype") // 改为多实例,默认为单实例
    public User getUser(){

        User user = new User();
        user.setName("楚留香");
        user.setAge(33);
        user.setGender("m");
        user.setId(1);
        return user;
    }

    @ConditionalOnClass(User.class) //放在方法级别，单独对这个方法进行注解判断。
    @Bean
    public Hanmeimei getHanmeimei(){
        return new Hanmeimei();
    }

    @ConditionalOnBean(name = "Hanmeimei")  //放在方法级别，单独对这个方法进行注解判断。
    @Bean
    public Lilei getLeilei(){

        return new Lilei();
    }

//    @Bean
//    public  FastsqlException fastsqlException(){
//        return new FastsqlException();
//    }

//    @Bean
//    @ConfigurationProperties(prefix = "car")
//    public Car car(){
//        return new Car();
//    }



}
