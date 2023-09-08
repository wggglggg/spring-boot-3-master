package com.sgg.springboot3.boot3;

import com.sgg.springboot3.boot3.config.BookContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(BookContainer.class)
@SpringBootApplication
public class Boot325ProblemDetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot325ProblemDetailApplication.class, args);
	}

}
