package com.sgg.springboot3.boot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * ClassName: CarConfig
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-02 8:37
 * @Version 1.0
 */

//@PropertySource(value = {"classpath:/car/car.yml"})
//@ConfigurationProperties(prefix = "carconfig")
@Data
public class CarConfig {

    private String color;
    private Integer seatNum;
}
