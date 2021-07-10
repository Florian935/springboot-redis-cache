package com.florian935.rediscache.service;

import com.florian935.rediscache.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> getAll();

    Optional<Post> getById(final Long id);

    Post insert(final Post post);

    Post update(final Post post);

    void deleteAll();

    void deleteById(final Long id);
}
