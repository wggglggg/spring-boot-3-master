package com.sgg.springboot3.boot.mapper;

import com.sgg.springboot3.boot.bean.User;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

/**
 * ClassName: UserMapper
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-23 15:13
 * @Version 1.0
 */

public interface UserMapper {

    /**
     * 1、每个方法都在Mapper文件中有一个sql标签对应。
     * 2、所有参数都应该用@Param进行签名，以后使用指定的名字在SQL中取值
     * @param id
     * @return
     */
    User getUserById(@Param("id") Integer id);

}
