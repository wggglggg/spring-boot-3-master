package com.sgg.springboot3.boot3102crudredis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class Employee implements Serializable {
    private Integer id;
    private String empName;
    private Integer age;
    private String email;
}
