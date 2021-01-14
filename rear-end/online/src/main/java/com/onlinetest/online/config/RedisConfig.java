package com.onlinetest.online.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author kevinhuang
 * @date 2020-06-20 17:09
 * redis 配置
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, String>redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, String>redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
}