package com.portfolio.blog.dto.post;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostDetailDto {
    private Long id;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Long memberId;
    private String memberName;
    private Long categoryId;
}
