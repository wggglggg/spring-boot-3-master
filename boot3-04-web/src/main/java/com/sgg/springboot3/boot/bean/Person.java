package com.sgg.springboot3.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Person
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-16 10:56
 * @Version 1.0
 */
@Data
@AllArgsConstructor     // 带一个有参构造器
@NoArgsConstructor      // 带一个无参构造器
public class Person {
    private Integer pid;
    private String name;
    private String favor;
    private String gender;
    private String email;
}
