package com.sgg.springboot3.boot;


import com.atguigu.boot3.starter.robot.controller.RobotController;
import com.atguigu.boot3.starter.robot.properties.RobotProperties;
import com.atguigu.boot3.starter.robot.service.RobotService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@EnableRobot
//@Import({RobotController.class, RobotService.class, RobotProperties.class})
@SpringBootApplication
public class Boot303LoggingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot303LoggingApplication.class, args);
    }

}
