package com.sgg.springboot3.boot.bean;

import lombok.Data;

/**
 * ClassName: User
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-23 15:14
 * @Version 1.0
 */
@Data
public class User {
    private Integer id;
    private String loginName;
    private String nickName;
    private String passwd;
}
