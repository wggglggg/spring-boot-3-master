package com.sgg.springboot3.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = "com.sgg.springboot3.boot")    // 自定义了Filter后需要设置扫描路径
@SpringBootApplication
public class Boot321ServletFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot321ServletFilterApplication.class, args);
    }

}
