package com.florian935.rediscache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {

        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<String, ?> redisTemplate() {

        final RedisTemplate<String, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());

        return template;
    }
}
