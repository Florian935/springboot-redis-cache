package com.florian935.rediscache.controller;

import com.florian935.rediscache.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1.0/cache")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CacheController {

    CacheService cacheService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public Set<String> getAll() {

        return cacheService.getAll();
    }

    @GetMapping(value = "/{key}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public Object getByKey(@PathVariable final String key) {

        return cacheService.getByKey(key);
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void evictAll() {

        cacheService.evictAll();
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(NO_CONTENT)
    public void evictByKey(@PathVariable final String key) {

        cacheService.evictByKey(key);
    }
}
