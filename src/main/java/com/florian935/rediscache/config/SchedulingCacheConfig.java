package com.florian935.rediscache.config;

import com.florian935.rediscache.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Slf4j
public class SchedulingCacheConfig {

    static final int EVICT_ALL_INTERVAL_IN_MILLIS = 3_600_000;
    CacheService cacheService;

    @Scheduled(fixedRate = EVICT_ALL_INTERVAL_IN_MILLIS)
    void evictAllCaches() {

        log.info("Scheduled every {} seconds (1 hour): Delete all caches", EVICT_ALL_INTERVAL_IN_MILLIS / 1000);
        cacheService.evictAll();
    }
}
