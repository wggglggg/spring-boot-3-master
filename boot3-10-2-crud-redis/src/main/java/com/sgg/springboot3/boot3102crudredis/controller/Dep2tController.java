package com.sgg.springboot3.boot3102crudredis.controller;

import com.sgg.springboot3.boot3102crudredis.bean.Dept;
import com.sgg.springboot3.boot3102crudredis.service.Dept2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: DeptController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-01 13:18
 * @Version 1.0
 */
@RestController
public class Dep2tController {

    @Autowired
    Dept2Service deptService;

    @GetMapping("/deptt/{id}")
    public Dept getDeptById(@PathVariable("id") Integer id){
        Dept dept = deptService.getDeptById(id);

        return dept;
    }
}
