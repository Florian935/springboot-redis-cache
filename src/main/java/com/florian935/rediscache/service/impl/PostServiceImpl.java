package com.florian935.rediscache.service.impl;

import com.florian935.rediscache.domain.Post;
import com.florian935.rediscache.repository.PostRepository;
import com.florian935.rediscache.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    CacheManager cacheManager;
    static final String SINGLE_CACHE = "post-single";
    static final String ALL_CACHE = "post-all";


    @Override
    @Cacheable(value = ALL_CACHE, key = "'post-all'")
    public List<Post> getAll() {
        System.out.println("=================> ALL");

        return IteratorUtils.toList(postRepository.findAll().iterator());
    }

    @Override
    @Cacheable(value = SINGLE_CACHE, key = "#id")
    public Post getById(Long id) {

        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Post not found !"));
    }

    @Override
    @CachePut(value = SINGLE_CACHE, key = "#post.id")
    public Post insert(Post post) {

        final Post savedPost = save(post);
        updateAllPostCache();

        return savedPost;
    }

    @Override
    @CachePut(value = SINGLE_CACHE, key = "#post.id")
    public Post update(Post post) {

        final Post updatedPost = save(post);
        updateAllPostCache();

        return save(post);
    }

    private void updateAllPostCache() {

        Objects.requireNonNull(cacheManager
                .getCache(ALL_CACHE))
                .put(ALL_CACHE, postRepository.findAll());
    }

    @Override
    public void deleteAll() {

        postRepository.deleteAll();
    }

    @Override
    @CacheEvict(value = SINGLE_CACHE, key = "#id")
    public void deleteById(Long id) {

        postRepository.deleteById(id);
        updateAllPostCache();
    }

    private Post save(final Post post) {
        return postRepository.save(post);
    }
}
