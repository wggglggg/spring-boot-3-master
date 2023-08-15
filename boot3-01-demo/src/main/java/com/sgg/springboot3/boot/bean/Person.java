package com.sgg.springboot3.boot.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName: Person
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-15 8:15
 * @Version 1.0
 */
@Data   //自动生成JavaBean属性的getter/setter/tostring/hash
@Component
@ConfigurationProperties(prefix = "person")  //和配置文件person前缀的所有配置进行绑定
//@NoArgsConstructor //自动生成无参构造器
//@AllArgsConstructor //自动生成全参构造器
public class Person {
    private String name;
    private Integer age;
    private Date birthDay;
    private Boolean like;
    private Child child; //嵌套对象
    private List<Dog> dogs; //数组（里面是对象）
    private Map<String,Cat> cats; //表示Map
}
