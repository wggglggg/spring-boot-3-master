package com.sgg.springboot3.boot312message;

import com.sgg.springboot3.boot312message.bean.Person;
import com.sgg.springboot3.boot312message.controller.TopicController;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;

@SpringBootTest
class Boot312MessageApplicationTests {

	@Autowired
	private KafkaTemplate kafkaTemplate;
	@Autowired
	private TopicController topicController;

	// 给sport 这个Topic发送了十万次消息， key为意大利， value为Pizza
	@Test
	void contextLoads() {

		StopWatch watch = new StopWatch();
		watch.start();
		CompletableFuture[] completableFutures = new CompletableFuture[100000];

		for (int i = 0; i < 100000; i++) {

			CompletableFuture sport = kafkaTemplate.send("sport", "意大利 - " + i, "Pizza - " + i);
			completableFutures[i] = sport;
		}
		// 等待所有的异步完成
		CompletableFuture.allOf(completableFutures).join();
		watch.stop();
		System.out.println("总耗时："+watch.getTotalTimeMillis());
	}

	// 给  这个topic发送一个消息 key为person, value为Person对象,一定要去properties配置value序列化器
	@Test
	public void testSendPerson(){
		CompletableFuture future = kafkaTemplate.send("music", "person", new Person(1, "江淑娜", "jsn@126.com"));

		future.join();
		System.out.println(" 消息发送成功---- ");
	}

	@Test
	public void testAddTopic(){
		NewTopic car = topicController.AddTopic("Car");
		System.out.println("car = " + car);
	}
}
