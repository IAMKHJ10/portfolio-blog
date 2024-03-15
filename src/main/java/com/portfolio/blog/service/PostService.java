package com.portfolio.blog.service;

import com.portfolio.blog.dto.PostDto;
import com.portfolio.blog.entity.Post;
import com.portfolio.blog.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void postWrite(PostDto postDto) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .hit(0)
                .build();
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<PostDto> postList() {
        List<Post> postList = postRepository.findAll();
        List<PostDto> postDtoList = new ArrayList<>();

        for (Post post : postList){
            PostDto postDto = PostDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .hit(post.getHit())
                    .createdDate(post.getCreatedDate())
                    .build();
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    @Transactional
    public PostDto findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("글을 찾을 수 없습니다."));

            PostDto postDto = PostDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .hit(post.getHit())
                    .createdDate(post.getCreatedDate())
                    .build();
        return postDto;
    }

    @Transactional
    public void updateHits(Long id){
        postRepository.updateHits(id);
    }

}
