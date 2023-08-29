package com.sgg.springboot3.boot312message.controller;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Controller;

/**
 * ClassName: TopicController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-28 21:57
 * @Version 1.0
 */
@Configuration
public class TopicController {



    // 添加一个新Topic
    @Bean
    public NewTopic AddTopic(){
        return TopicBuilder.name("Car")
                .partitions(1)
                .compact()
                .build();
    }
}
