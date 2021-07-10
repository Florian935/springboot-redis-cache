package com.florian935.rediscache.service.impl;

import com.florian935.rediscache.service.CacheService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CacheServiceImpl implements CacheService {

    static String WILDCARD = "*";
    CacheManager cacheManager;
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public Set<String> getAll() {

        return redisTemplate.keys(WILDCARD);
    }

    @Override
    public Object getByKey(String key) {

        return redisTemplate.keys(String.format("%s%s", key, WILDCARD));
    }

    @Override
    public void evictAll() {

        cacheManager
                .getCacheNames()
                .forEach(cacheName ->
                        Objects.requireNonNull(cacheManager.getCache(cacheName)).clear()
                );
    }

    @Override
    public void evictByKey(String key) {

        Objects.requireNonNull(cacheManager.getCache(key)).clear();
    }
}
