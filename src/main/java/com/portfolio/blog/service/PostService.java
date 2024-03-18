package com.portfolio.blog.service;

import com.portfolio.blog.dto.PostDto;
import com.portfolio.blog.dto.common.MessageDto;
import com.portfolio.blog.entity.Post;
import com.portfolio.blog.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public ResponseEntity<?> save(PostDto postDto) {
        Post newPost = Post.createPost(postDto);
        postRepository.save(newPost);
        return ResponseEntity.ok().body(new MessageDto<>("success", ""));
    }

    @Transactional
    public ResponseEntity<?> update(PostDto postDto) {
        Post findPost = postRepository.findById(postDto.getId())
                .orElseThrow(()-> new IllegalArgumentException("해당 글을 찾을 수 없습니다."));

        Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();
        return ResponseEntity.ok().body(new MessageDto<>("success", ""));
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
