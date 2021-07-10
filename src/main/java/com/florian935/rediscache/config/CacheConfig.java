package com.florian935.rediscache.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@EnableCaching
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CacheConfig {

    RedisProperties redisProperties;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {

        final RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration(redisProperties.getHostname(), redisProperties.getPort());
        redisStandaloneConfiguration.setDatabase(redisProperties.getDatabaseInstance());

        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate() {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
