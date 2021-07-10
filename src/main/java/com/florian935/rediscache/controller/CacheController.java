package com.florian935.rediscache.controller;

import com.florian935.rediscache.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/api/v1.0/cache")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CacheController {

    CacheService cacheService;

    @GetMapping
    public Set<String> getAll() {

        log.info("Get all cache");

        return getCacheService().getAll();
    }

    @GetMapping("/{key}")
    public Object getByKey(@PathVariable final String key) {

        log.info("Get cache by key {}", key);

        return getCacheService().getByKey(key);
    }

    @DeleteMapping
    public void evictAll() {

        log.info("Delete all cache");

        getCacheService().evictAll();
    }

    @DeleteMapping("/{key}")
    public void evictByKey(@PathVariable final String key) {

        log.info("Delete cache by key {}", key);

        getCacheService().evictByKey(key);
    }
}
