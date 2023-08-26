package com.sgg.springboot3.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


/**
 * ClassName: Person
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-26 19:13
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private Date birthDay;
}
