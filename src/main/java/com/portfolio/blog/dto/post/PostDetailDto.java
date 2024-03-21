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
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private String memberId;
    private String memberName;
}
