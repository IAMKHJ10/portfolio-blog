package com.portfolio.blog.service;

import com.portfolio.blog.dto.common.MessageDto;
import com.portfolio.blog.dto.post.PostDetailDto;
import com.portfolio.blog.dto.post.PostListDto;
import com.portfolio.blog.dto.post.PostSaveDto;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.entity.Post;
import com.portfolio.blog.repository.member.MemberRepository;
import com.portfolio.blog.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ResponseEntity<?> save(PostSaveDto postSaveDto) {

        Optional<Member> member = memberRepository.findByUid(postSaveDto.getMemberId());

        if(member.isPresent()){
            Member memberEntity = member.get();

            Post newPost = Post.builder()
                    .title(postSaveDto.getTitle())
                    .content(postSaveDto.getContent())
                    .hit(0)
                    .member(memberEntity)
                    .build();

            postRepository.save(newPost);

            return ResponseEntity.ok().body(new MessageDto<>("success", ""));
        }else{
            return ResponseEntity.ok().body(new MessageDto<>("fail", ""));
        }
    }

    @Transactional
    public ResponseEntity<?> update(PostSaveDto postDto) {
        Post findPost = postRepository.findById(postDto.getId())
                .orElseThrow(()-> new IllegalArgumentException("해당 글을 찾을 수 없습니다."));

        Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();
        return ResponseEntity.ok().body(new MessageDto<>("success", ""));
    }

    @Transactional(readOnly = true)
    public List<PostListDto> findAll() {
        List<Post> posts = postRepository.findAll();
        List<PostListDto> list = new ArrayList<>();

        for (Post post : posts){
            Member member = post.getMember();
            PostListDto dto = PostListDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .hit(post.getHit())
                    .memberName(member.getName())
                    .createdDate(post.getCreatedDate())
                    .build();
            list.add(dto);
        }

        return list;
    }

    @Transactional(readOnly = true)
    public PostDetailDto findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 글을 찾을 수 없습니다."));

        Member member = post.getMember();

        return PostDetailDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .hit(post.getHit())
                .createdDate(post.getCreatedDate())
                .lastModifiedDate(post.getLastModifiedDate())
                .memberName(member.getName())
                .build();
    }

    @Transactional
    public void updateHits(Long id){
        postRepository.updateHits(id);
    }

}
