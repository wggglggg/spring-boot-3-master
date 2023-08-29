package com.sgg.springboot3.boot3102crudredis.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

/**
 * ClassName: AppRedisConfiguration
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-27 15:13
 * @Version 1.0
 */
@Configuration
public class AppRedisConfiguration {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
