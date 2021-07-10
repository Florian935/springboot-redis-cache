package com.florian935.rediscache.controller;

import com.florian935.rediscache.domain.Post;
import com.florian935.rediscache.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1.0/posts")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PostController {

    PostService postService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    List<Post> getAll() {

        return postService.getAll();
    }

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

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    Post update(@RequestBody @Valid final Post post) {

        return postService.update(post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    void deleteById(@PathVariable final Long id) {

        postService.deleteById(id);
    }
}
