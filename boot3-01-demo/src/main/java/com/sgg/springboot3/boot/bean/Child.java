package com.sgg.springboot3.boot.bean;

import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * ClassName: Child
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-15 8:16
 * @Version 1.0
 */
@Data
public class Child {
    private String name;
    private Integer age;
    private Date birthDay;
    private List<String> text; //数组

}
