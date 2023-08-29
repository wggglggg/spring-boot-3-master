package com.sgg.springboot3.boot312message.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Person
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-28 21:06
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    private Integer id;
    private String name;
    private String email;
}
