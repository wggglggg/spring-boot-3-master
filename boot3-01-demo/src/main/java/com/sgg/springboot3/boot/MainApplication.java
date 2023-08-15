package com.sgg.springboot3.boot;

import com.sgg.springboot3.boot.bean.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * ClassName: MainApplication
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/8/13 19:56
 * @Version 1.0
 */
@SpringBootApplication  //这是一个SpringBoot应用
public class MainApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ioc = SpringApplication.run(MainApplication.class);

        Person person = ioc.getBean(Person.class);
        System.out.println("person = " + person);
    }
}
