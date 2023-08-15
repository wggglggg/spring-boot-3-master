package com.sgg.springboot3.boot;

import com.alibaba.druid.FastsqlException;
import com.sgg.springboot3.boot.bean.Car;
import com.sgg.springboot3.boot.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;




//@SpringBootApplication(scanBasePackages = "com.sgg.springboot3.boot.bean")
@SpringBootApplication
//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan("com.sgg.springboot3.boot")
public class Boot302DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(Boot302DemoApplication.class, args);


        for (String name : ioc.getBeanDefinitionNames()) {
            System.out.println("name = " + name);
        }


//        String[] beanNamesForType = ioc.getBeanNamesForType(User.class);
////        String[] beanNamesForType1 = ioc.getBeanNamesForType(FastsqlException.class);
////
//        for (String s : beanNamesForType) {
//            System.out.println("s = " + s);
//        }
//
//        User chuliuxiang = (User) ioc.getBean("chuliuxiang");
//        User chuliuxiang2 = (User) ioc.getBean("chuliuxiang");
//
//        System.out.println(chuliuxiang ==chuliuxiang2);     // true 单实例

        Car car = (Car) ioc.getBean(Car.class);
        System.out.println("car = " + car); //car = Car{id=1001, brand='BYD', color='red'}

    }





}
