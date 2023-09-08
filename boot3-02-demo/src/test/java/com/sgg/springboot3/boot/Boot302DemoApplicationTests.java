package com.sgg.springboot3.boot;

import com.sgg.springboot3.boot.config.AppConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Boot302DemoApplicationTests {

    @Autowired
    private AppConfig appConfig;




//    @Test
//    void contextLoads() {
//        appConfig.print();
//    }

}
