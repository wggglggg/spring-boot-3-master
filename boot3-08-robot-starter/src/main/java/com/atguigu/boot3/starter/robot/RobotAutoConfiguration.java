package com.atguigu.boot3.starter.robot;

import com.atguigu.boot3.starter.robot.controller.RobotController;
import com.atguigu.boot3.starter.robot.properties.RobotProperties;
import com.atguigu.boot3.starter.robot.service.RobotService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * ClassName: RobotAutoConfiguration
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-25 12:25
 * @Version 1.0
 */
@Configuration
//给容器中导入Robot功能要用的所有组件
@Import({RobotProperties.class, RobotController.class, RobotService.class})
public class RobotAutoConfiguration {
}
