package com.sgg.springboot3.boot.mapper;

import com.sgg.springboot3.boot.bean.User;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: UserMapper
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-01 9:39
 * @Version 1.0
 */
public interface UserMapper {

    User getUserById(@Param("id") Integer id);
}
