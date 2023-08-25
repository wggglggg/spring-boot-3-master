package com.sgg.springboot3.boot307core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: User
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-24 14:49
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userName;
    private String password;
}
