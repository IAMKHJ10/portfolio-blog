package com.portfolio.blog.service;

import com.portfolio.blog.entity.Post;
import com.portfolio.blog.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void save(Post post) {
        postRepository.save(post);
    }

    @Transactional
    public void update(Post post) {
        Post findPost = postRepository.findById(post.getId())
                .orElseThrow(()-> new IllegalArgumentException("해당 글을 찾을 수 없습니다."));

        postRepository.save(findPost);
    }

    @Transactional(readOnly = true)
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 글을 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Transactional
    public void updateHits(Long id){
        postRepository.updateHits(id);
    }

}
