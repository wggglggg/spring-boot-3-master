package com.sgg.springboot3.boot.controller;

import com.sgg.springboot3.boot.bean.Dept;
import com.sgg.springboot3.boot.service.DeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: DeptController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-27 7:57
 * @Version 1.0
 */
@Tag(name = "部门", description = "部门CRUD")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Operation(summary = "根据ID查询某部门")
    @GetMapping("/dept/{id}")
    public Dept getDept(@PathVariable("id") Integer id){
        Dept dept = deptService.getDept(id);

        return dept;
    }

    @Operation(summary = "添加新部门", description = "接受一个Dept类型为参数")
    @PostMapping("/dept")
    public String saveDept(@RequestBody @Parameter(description = "Dept类型") Dept dept){

        deptService.saveDept(dept);
        return "ok";
    }

    @Operation(summary = "查询所有部门")
    @GetMapping("/depts")
    public List<Dept> getDepts(){

        return deptService.getDepts();
    }

    @Operation(summary = "根据ID删除某部门")
    @DeleteMapping("/dept/{id}")
    public String deleteDept(@PathVariable("id") Integer id){
        deptService.deleteDept(id);

        return "deleted ok";
    }

}
