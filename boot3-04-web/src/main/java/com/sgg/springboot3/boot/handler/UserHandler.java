package com.sgg.springboot3.boot.handler;

import com.sgg.springboot3.boot.bean.Person;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: UserHandler
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-22 15:56
 * @Version 1.0
 */
@Component
@Slf4j
public class UserHandler {

    public ServerResponse getUser(ServerRequest request){
        String id = request.pathVariable("id");
        log.info("查询 【{}】 用户信息，数据库正在检索", id);
        Person person = new Person(1, "蔡灿得", "演戏", "女", "caicande@126.com");

        return ServerResponse.ok().body(person);
    }


    public ServerResponse getUsers(ServerRequest request) {

        List<Person> list = Arrays.asList(new Person(1, "蔡灿得", "演戏", "女", "caicande@126.com"), new Person(2, "蔡幸娟", "唱歌", "女", "caixingjuan@126.com"));

        return ServerResponse.ok().body(list);
    }

    public ServerResponse saveUser(ServerRequest request) throws ServletException, IOException {

        Person body = request.body(Person.class);

        log.info("添加用户信息：{}",body);
        return ServerResponse.ok().build();
    }

    public ServerResponse updateUser(ServerRequest request) {
        String id = request.pathVariable("id");
        log.info("修改用户信息：{}", id);
        return ServerResponse.ok().build();
    }


    public ServerResponse deleteUser(ServerRequest request) {
        String id = request.pathVariable("id");
        log.info("删除用户信息：{}", id);
        return ServerResponse.ok().build();
    }
}
