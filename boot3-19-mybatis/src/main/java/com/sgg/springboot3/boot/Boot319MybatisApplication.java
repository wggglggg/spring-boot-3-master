package com.sgg.springboot3.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.sgg.springboot3.boot.mapper")
@SpringBootApplication
public class Boot319MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot319MybatisApplication.class, args);
    }

}
