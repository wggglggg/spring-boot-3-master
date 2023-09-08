package com.sgg.springboot3.boot.service;

import com.sgg.springboot3.boot.modle.Todo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

/**
 * ClassName: TodoService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-08 11:06
 * @Version 1.0
 */
public interface TodoService {

    /**
     * https://jsonplaceholder.typicode.com
     * 根据ID查询待办事项
     * todo = Todo(userId=5, id=100, title=excepturi a et neque qui expedita vel voluptate, completed=false)
     * @param id
     * @return
     */
    @GetExchange("/todos/{id}")
    public Todo getTodoById(@PathVariable("id") Integer id);

    @PostExchange(value = "/todos", accept = MediaType.APPLICATION_JSON_VALUE)
    public Todo createNewTodo(@RequestBody Todo newTodo);

    @PutExchange("/todos/{id}")
    public Todo editTodo(@PathVariable Integer id);

    @DeleteExchange("/todos/{id}")
    public Todo deleteTodo(@PathVariable Integer id);
}
