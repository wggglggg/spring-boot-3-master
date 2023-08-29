package com.sgg.springboot3.boot3102crudredis.service;

import com.sgg.springboot3.boot3102crudredis.bean.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: DeptService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-27 7:59
 * @Version 1.0
 */
@Service
public class DeptService {

//    Map<Integer, Dept> data = new ConcurrentHashMap<Integer, Dept>();
//
//    public DeptService() {
//        data.put(1, new Dept(1, "客服部"));
//        data.put(2, new Dept(2, "综合部"));
//        data.put(3, new Dept(3, "维修部"));
//    }

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 获取部门信息
     * @param id
     * @return
     */
    public Dept getDept(Integer id){
        String deptName = "deptList";
        return (Dept) redisTemplate.opsForList().index(deptName, id-1);
    }

    /**
     * 保存新部门
     * @param dept
     */
    public void saveDept(Dept dept){
        String deptName = "deptList";
        redisTemplate.opsForList().rightPush(deptName, dept);
    }

    /**
     * 获取所有部门信息
     * @return
     */
    public List<Dept> getDepts(){
        String deptName = "deptList";
        return redisTemplate.opsForList().range(deptName, 0, -1);
    }

    /**
     * 删除某个部门
     * @param id
     */
    public void deleteDept(Integer id){
        String deptName = "deptList";

        Dept dept = getDept(id);
        redisTemplate.opsForList().remove(deptName, 1, dept);
    }

}
