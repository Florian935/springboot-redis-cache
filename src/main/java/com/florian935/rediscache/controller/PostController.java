package com.florian935.rediscache.controller;

import com.florian935.rediscache.domain.Post;
import com.florian935.rediscache.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PostController {

    static final String ALL_CACHE = "post-all";
    static final String SINGLE_CACHE = "post-single";
    PostService postService;

    @Cacheable(value = SINGLE_CACHE, key = "#id")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    Post getById(@PathVariable final Long id) {

        return postService.getById(id);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    Post insert(@RequestBody @Valid final Post post) {

        return postService.insert(post);
    }

    @CachePut(value = SINGLE_CACHE, key = "#post.id")
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(ACCEPTED)
    Post update(@RequestBody @Valid final Post post) {

        return postService.update(post);
    }

    @CacheEvict(value = SINGLE_CACHE, key = "#id")
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    void deleteById(@PathVariable final Long id) {

        postService.deleteById(id);
    }
}
