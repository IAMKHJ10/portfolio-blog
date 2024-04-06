package com.portfolio.blog.service;

import com.portfolio.blog.dto.MessageDto;
import com.portfolio.blog.dto.post.PostDetailDto;
import com.portfolio.blog.dto.post.PostListDto;
import com.portfolio.blog.dto.post.PostSaveDto;
import com.portfolio.blog.dto.post.PostUpdateDto;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.entity.Post;
import com.portfolio.blog.repository.member.MemberRepository;
import com.portfolio.blog.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final FileService fileService;

    @Transactional
    public MessageDto<?> save(PostSaveDto dto) throws IOException {

        Optional<Member> member = memberRepository.findById(dto.getMemberId());

        if(member.isPresent()){ // 글쓴이 정보가 있으면
            Member memberEntity = member.get();

            Post newPost = Post.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .member(memberEntity)
                    .build();

            Post post = postRepository.save(newPost);
            fileService.saveWithPost(dto.getFile(), post);

            return new MessageDto<>("ok", newPost.getId());
        }else{ // 글쓴이가 삭제된 상태
            return new MessageDto<>("no");
        }
    }

    @Transactional
    public MessageDto<?> update(Long id, PostUpdateDto dto) throws IOException {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 글을 찾을 수 없습니다."));

        post.update(dto);

        if(dto.getFile()!=null && !dto.getFile().isEmpty()){//파일 있으면
            boolean result = fileService.delete(post.getFiles().get(0)); //실제파일 삭제성공 여부

            if(result){//실제 파일 성공하고 새로운 받은 파일 업로드
                fileService.saveWithPost(dto.getFile(), post);
                return new MessageDto<>("ok", post.getId());
            }else{
                return new MessageDto<>("false");
            }
        }

        return new MessageDto<>("ok", post.getId());
    }

    @Transactional(readOnly = true)
    public Slice<PostListDto> findAllSlice(Pageable pageable) {
        Slice<Post> posts = postRepository.findAll(pageable);
        return posts
                .map(PostListDto::new);
    }

    @Transactional(readOnly = true)
    public Page<PostListDto> findAll(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작
        int pageLimit = 10; // 한페이지에 보여줄 글 개수
        Page<Post> posts = postRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC,"id")));
        return posts
                .map(PostListDto::new);
    }

    @Transactional(readOnly = true)
    public PostDetailDto findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 글을 찾을 수 없습니다."));

        return PostDetailDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .hit(post.getHit())
                .createdDate(post.getCreatedDate())
                .lastModifiedDate(post.getLastModifiedDate())
                .member(post.getMember())
                .file(post.getFiles().get(0))
                .build();
    }

    @Transactional
    public MessageDto<?> delete(Long id) {
        Optional<Post> post = postRepository.findById(id);
        fileService.delete(post.get().getFiles().get(0));
        postRepository.deleteById(id);
        return new MessageDto<>("ok");
    }

    @Transactional
    public void updateHits(Long id){
        postRepository.updateHits(id);
    }

}
