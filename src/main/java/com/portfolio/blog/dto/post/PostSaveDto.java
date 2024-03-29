package com.portfolio.blog.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSaveDto {
    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private Long categoryId;
}
