package com.portfolio.blog.dto.post;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDetailDto {
    private Long id;
    private String title;
    private String content;
    private int hit;
    private String memberName;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
