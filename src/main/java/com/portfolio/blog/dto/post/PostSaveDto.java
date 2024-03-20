package com.portfolio.blog.dto.post;

import lombok.Data;

@Data
public class PostSaveDto {
    private Long id;
    private String title;
    private String content;
    private String memberId;
}
