package com.portfolio.blog.entity;

import com.portfolio.blog.dto.PostDto;
import com.portfolio.blog.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    private int hit;

    @Builder
    public Post(String title, String content, int hit) {
        this.title = title;
        this.content = content;
        this.hit = hit;
    }

    public static Post createPost(PostDto postDto){
        return Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .hit(0)
                .build();
    }

}
