package com.sgg.springboot3.boot3102crudredis.controller;

import com.sgg.springboot3.boot3102crudredis.bean.Employee;
import com.sgg.springboot3.boot3102crudredis.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: EmployeeController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-27 8:30
 * @Version 1.0
 */
@Tag(name = "客户", description = "客户CRUD")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "根据ID查询某客户", description = "接受参数为Integer id")
    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        return employeeService.getEmployee(id);
    }

    @Operation(summary = "查询 所有客户信息")
    @GetMapping("/emps")
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @Operation(summary = "添加一个新客户", description = "接受参数为Employee类型")
    @PostMapping("/emp")
    public String saveEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);

        return "保存成功";
    }

    @Operation(summary = "根据ID删除某个客户", description = "参数为Integer id")
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeService.deleteEmployee(id);

        return "删除成功";
    }
}
