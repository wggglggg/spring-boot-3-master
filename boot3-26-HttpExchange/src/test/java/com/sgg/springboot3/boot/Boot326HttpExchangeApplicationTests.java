package com.sgg.springboot3.boot;

import com.sgg.springboot3.boot.config.HttpExchangeConfiguration;
import com.sgg.springboot3.boot.modle.Todo;
import com.sgg.springboot3.boot.service.AlbumsService;
import com.sgg.springboot3.boot.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Boot326HttpExchangeApplicationTests {

	@Autowired
	private TodoService requestHttpService;

	@Autowired
	private AlbumsService albumsService;

	/**
	 * todo = Todo(userId=5, id=100, title=excepturi a et neque qui expedita vel voluptate, completed=false)
	 */
	@Test
	void testGetTodoById(Integer id) {
		Todo todo = requestHttpService.getTodoById(id);
		System.out.println("todo = " + todo);
	}

	@Test
	public void testCreateNewTodo(){
		Todo todo = new Todo();
		todo.setId(201);
		todo.setUserId(16);
		todo.setTitle("clean PC");
		todo.setCompleted(false);

		Todo newTodo = requestHttpService.createNewTodo(todo);
		System.out.println("newTodo = " + newTodo);
	}

	@Test
	public void testEditTodo(){
		Todo todo = requestHttpService.editTodo(200);
		todo.setTitle("修理109号机器");
		todo.setUserId(40);
		todo.setCompleted(true);

		System.out.println("todo = " + todo);

	}

	@Test
	public void testDeleteTodo(){
		Todo todo = requestHttpService.deleteTodo(2);
		todo.setUserId(27);
		todo.setTitle("测试打汁机");
		todo.setCompleted(false);

		System.out.println("todo = " + todo);

		Todo todoById = requestHttpService.getTodoById(2);
		System.out.println("todoById = " + todoById);
	}





}
