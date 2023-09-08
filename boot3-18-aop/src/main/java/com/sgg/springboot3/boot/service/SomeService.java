package com.sgg.springboot3.boot.service;


import org.springframework.stereotype.Service;

/**
 * ClassName: CarTest
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-02 13:55
 * @Version 1.0
 */
@Service
public class SomeService {

    public void show(){
        System.out.println("This is show method" );
    }

    public void run(Integer gear){
        System.out.println("挡位是gear = " + gear);
    }

}
