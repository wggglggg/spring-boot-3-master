package com.example.boot;

import com.example.boot.blog.settings.ArticleSettings;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan(basePackages = "com.example.boot.blog.mapper")
@EnableConfigurationProperties({ArticleSettings.class})
@SpringBootApplication
public class Boot327BlogAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot327BlogAdminApplication.class, args);
	}

}
