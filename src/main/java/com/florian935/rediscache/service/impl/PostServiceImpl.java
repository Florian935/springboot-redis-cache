package com.florian935.rediscache.service.impl;

import com.florian935.rediscache.domain.Post;
import com.florian935.rediscache.repository.PostRepository;
import com.florian935.rediscache.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    static final String SINGLE_CACHE = "post-single";
    static final String ALL_CACHE = "post-all";


    @Override
    @Cacheable(value = ALL_CACHE)
    public List<Post> getAll() {

        return IteratorUtils.toList(postRepository.findAll().iterator());
    }

    @Override
    @Cacheable(value = SINGLE_CACHE, key = "#id")
    public Optional<Post> getById(Long id) {

        return postRepository.findById(id);
    }

    @Override
    @CachePut(value = SINGLE_CACHE, key = "#post.id")
    public Post insert(Post post) {

        return save(post);
    }

    @Override
    @CachePut(value = SINGLE_CACHE, key = "#post.id")
    public Post update(Post post) {

        return save(post);
    }

    @Override
    public void deleteAll() {

        postRepository.deleteAll();
    }

    @Override
    @CacheEvict(value = SINGLE_CACHE, key = "#id")
    public void deleteById(Long id) {

        postRepository.deleteById(id);
    }

    private Post save(final Post post) {
        return postRepository.save(post);
    }
}
