package com.florian935.rediscache.service;

import java.util.Set;

public interface CacheService {

    Set<String> getAll();

    Object getByKey(final String key);

    void evictAll();

    void evictByKey(final String key);
}
