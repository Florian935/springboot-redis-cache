package com.florian935.rediscache.repository;

import com.florian935.rediscache.domain.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
