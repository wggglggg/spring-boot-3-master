package com.sgg.springboot3.boot.service;

import com.sgg.springboot3.boot.bean.User;
import com.sgg.springboot3.boot.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-23 15:44
 * @Version 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserController userController;

    public User getUserById(Integer id){
        User user = userController.getUserById(id);

        return user;
    }
}
