package com.florian935.rediscache.service.impl;

import com.florian935.rediscache.domain.Post;
import com.florian935.rediscache.repository.PostRepository;
import com.florian935.rediscache.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    @Override
    public List<Post> getAll() {

        return IteratorUtils.toList(postRepository.findAll().iterator());
    }

    @Override
    public Post getById(Long id) {

        return postRepository.findById(id).get();
    }

    @Override
    public Post insert(Post post) {

        return save(post);
    }

    @Override
    public Post update(Post post) {

        return save(post);
    }

    @Override
    public void deleteAll() {

        postRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {

        postRepository.deleteById(id);
    }

    private Post save(final Post post) {
        return postRepository.save(post);
    }
}
