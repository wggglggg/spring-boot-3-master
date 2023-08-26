package com.sgg.springboot3.boot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

/**
 * ClassName: MyRedisConfiguration
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-26 19:33
 * @Version 1.0
 */
@Configuration
public class MyRedisConfiguration {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        // 自定义Redis将对象写入到数据库中的类型，把对象转为json字符串的序列化工具，要先将Person Implement Serializable
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }
}
