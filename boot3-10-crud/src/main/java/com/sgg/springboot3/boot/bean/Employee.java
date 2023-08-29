package com.sgg.springboot3.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Employee
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-27 7:55
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer id;
    private String empName;
    private Integer age;
    private String email;
}
