package com.sgg.springboot3.boot.controller;

import com.sgg.springboot3.boot.bean.User;
import com.sgg.springboot3.boot.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: UserController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-23 15:41
 * @Version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 返回User的json数据
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        User user = userMapper.getUserById(id);

        return user;
    }
}
