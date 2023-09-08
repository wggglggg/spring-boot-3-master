package com.sgg.springboot3.boot3102crudredis.service;

import com.sgg.springboot3.boot3102crudredis.bean.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * ClassName: DeptService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-01 13:14
 * @Version 1.0
 */
@Service
public class Dept2Service {

    @Autowired
    RedisTemplate redisTemplate;

    public Dept getDeptById(Integer id){
        String listName = "deptList";

        Dept dept = (Dept) redisTemplate.opsForList().index(listName, id - 1);

        return dept;
    }
}
