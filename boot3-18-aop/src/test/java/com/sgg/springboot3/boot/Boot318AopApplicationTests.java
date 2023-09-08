package com.sgg.springboot3.boot;


import com.sgg.springboot3.boot.service.SomeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Boot318AopApplication.class)
class Boot318AopApplicationTests {

    @Autowired
    SomeService service;

    @Test
    public void testShow(){
        service.show();
    }

    @Test
    public void testRun(){
        service.run(3);
    }
}
