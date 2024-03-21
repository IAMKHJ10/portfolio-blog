package com.portfolio.blog.service;

import com.portfolio.blog.dto.post.PostDetailDto;
import com.portfolio.blog.dto.post.PostListDto;
import com.portfolio.blog.dto.post.PostSaveDto;
import com.portfolio.blog.dto.post.PostUpdateDto;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.entity.Post;
import com.portfolio.blog.repository.member.MemberRepository;
import com.portfolio.blog.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
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
    public void save(PostSaveDto dto) {

        Optional<Member> member = memberRepository.findByUid(dto.getMemberId());

        if(member.isPresent()){
            Member memberEntity = member.get();

            Post newPost = Post.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .member(memberEntity)
                    .build();

            postRepository.save(newPost);

        }else{
        }
    }

    @Transactional
    public void update(Long id, PostUpdateDto dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 글을 찾을 수 없습니다."));

        post.update(dto);

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
                .memberId(member.getUid())
                .memberName(member.getName())
                .build();
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);

    }

    @Transactional
    public void updateHits(Long id){
        postRepository.updateHits(id);
    }

}
