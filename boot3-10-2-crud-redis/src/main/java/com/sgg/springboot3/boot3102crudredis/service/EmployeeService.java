package com.sgg.springboot3.boot3102crudredis.service;

import com.sgg.springboot3.boot3102crudredis.bean.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: EmployeeService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-27 8:31
 * @Version 1.0
 */
@Service
public class EmployeeService {

    Map<Integer, Employee> data = new ConcurrentHashMap<>();

    public EmployeeService() {
        data.put(1, new Employee(1, "蔡幸娟", 55, "cxj@126.com"));
        data.put(2, new Employee(2, "林晏如", 45, "lyr@126.com"));
    }

    /**
     * 获取某个客户信息
     * @param id
     * @return
     */
    public Employee getEmployee(Integer id){
        return data.get(id);
    }

    /**
     * 获取所有客户信息
     * @return
     */
    public List<Employee> getEmployees(){
        return data.values().stream().toList();
    }

    /**
     * 保存客户信息
     * @param employee
     */
    public void saveEmployee(Employee employee){
        data.put(employee.getId(), employee);
    }

    /**
     * 删除某个用户信息
     * @param id
     */
    public void deleteEmployee(Integer id){
        data.remove(id);
    }
}
