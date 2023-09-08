package com.sgg.springboot3.boot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: Car
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/8/14 11:41
 * @Version 1.0
 */

//@ConfigurationProperties(prefix = "car")   // 可以使用application.properties来设置值 比如car.color=red
//@Component
public class Car {
    private Integer id;
    private String brand;
    private String color;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
