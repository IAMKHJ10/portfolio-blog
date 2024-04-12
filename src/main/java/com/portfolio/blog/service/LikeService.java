package com.portfolio.blog.service;

import com.portfolio.blog.dto.MessageDto;
import com.portfolio.blog.dto.like.LikePostDto;
import com.portfolio.blog.dto.user.LoginSessionDto;
import com.portfolio.blog.entity.Likes;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.entity.Post;
import com.portfolio.blog.repository.like.LikeRepository;
import com.portfolio.blog.repository.member.MemberRepository;
import com.portfolio.blog.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MessageDto<?> likePost(LikePostDto dto) {

        Optional<Member> member = memberRepository.findById(dto.getMemberId());
        if(member.isEmpty()) return new MessageDto<>("noMember");

        Optional<Post> post = postRepository.findById(dto.getPostId());
        if(post.isEmpty()) return new MessageDto<>("noPost");

        // 좋아요 누른 적 있는지 확인
        Optional<Likes> foundLikes = likeRepository.findByPostAndMember(post.get(), member.get());

        if(foundLikes.isEmpty()){ // 좋아요 누른적 없음
            Likes likes = Likes.builder()
                    .post(post.get())
                    .member(member.get())
                    .build();
            likeRepository.save(likes);
            return new MessageDto<>("ok");
        }else{ // 좋아요 누른 적 있음
            likeRepository.delete(foundLikes.get());
            return new MessageDto<>("cancel");
        }

    }

    public Boolean findByPostAndMember(Long postId, LoginSessionDto user) {
        Optional<Member> member = memberRepository.findById(user==null?0:user.getId());
        Optional<Post> post = postRepository.findById(postId);
        if(member.isPresent()&&post.isPresent()){
            Optional<Likes> likes = likeRepository.findByPostAndMember(post.get(), member.get());
            return likes.isPresent();
        }
        return false;
    }
}
