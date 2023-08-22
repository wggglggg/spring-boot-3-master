package com.sgg.springboot3.boot.controller;



import com.sgg.springboot3.boot.bean.Person;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: getNameController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-15 19:48
 * @Version 1.0
 */
@Slf4j
@RestController
public class getNameController {



    /**
     * 默认使用新版 PathPatternParser 进行路径匹配
     * 不能匹配 ** 在中间的情况，剩下的和 antPathMatcher语法兼容
     * @param request
     * @param path
     * @return
     */
    // # path_pattern_parser 新版策略；只允许在网络地址patter最后加上 /**
    @GetMapping("/a*/b?/{v:[0-9]+}/**")
    public String hello(@PathVariable("v") Integer v, HttpServletRequest request){

        log.info("路径变量v： {}", v);

        //获取请求路径
        String requestURI = request.getRequestURI();
        return requestURI;
    }

    //使用老版方式 ant_path_matcher 老版策略；允许在网络中间patter加上 /**
    @GetMapping("/a*/b?/**/{v:[0-9]+}") // 例如访问http://localhost:8080/aaa/bb/a1b2c3/9
    public String hello2(@PathVariable("v") Integer v, HttpServletRequest request){

        log.info("路径变量v： {}", v);

        //获取请求路径
        String requestURI = request.getRequestURI();    // requestURI =   /aaa/bb/a1b2c3/9
        return requestURI;
    }
    /**
     * 1、默认支持把对象写为json。因为默认web场景导入了jackson处理json的包;jackson-core
     * 2、jackson也支持把数据写为xml。导入xml相关依赖
     * @return
     */
    @GetMapping("/person")
    public Person person(){
        Person person = new Person();
        person.setPid(1);
        person.setName("成龙");
        person.setFavor("拍戏");
        person.setGender("男");
        person.setEmail("jack@126.com");

        return person;
    }


}
