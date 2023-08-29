package com.sgg.springboot3.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Dept
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-27 7:54
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer id;
    private String deptName;
}
