package com.sgg.springboot3.boot.bean;

import java.io.PrintStream;

/**
 * ClassName: Sheep
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/8/14 11:21
 * @Version 1.0
 */
public class Sheep {
    private Integer id;
    private String name;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
