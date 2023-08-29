package com.sgg.springboot3.boot.service;

import com.sgg.springboot3.boot.bean.Dept;
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

    Map<Integer, Dept> data = new ConcurrentHashMap<Integer, Dept>();

    public DeptService() {
        data.put(1, new Dept(1, "客服部"));
        data.put(2, new Dept(2, "综合部"));
        data.put(3, new Dept(3, "维修部"));
    }


    /**
     * 获取部门信息
     * @param id
     * @return
     */
    public Dept getDept(Integer id){

        return data.get(id);
    }

    /**
     * 保存新部门
     * @param dept
     */
    public void saveDept(Dept dept){
        data.put(dept.getId(), dept);
    }

    /**
     * 获取所有部门信息
     * @return
     */
    public List<Dept> getDepts(){
        return data.values().stream().toList();
    }

    /**
     * 删除某个部门
     * @param id
     */
    public void deleteDept(Integer id){
        data.remove(id);
    }

}
