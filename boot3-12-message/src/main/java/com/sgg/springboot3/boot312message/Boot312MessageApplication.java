package com.sgg.springboot3.boot312message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka	// 发送Person Emp等类型时，要用此注解
@SpringBootApplication
public class Boot312MessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot312MessageApplication.class, args);
	}

}
