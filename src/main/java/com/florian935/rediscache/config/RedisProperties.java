package com.florian935.rediscache.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@ConfigurationProperties(prefix = "redis.config")
public class RedisProperties {

    String hostname;
    Integer port;
    Integer databaseInstance;
}
