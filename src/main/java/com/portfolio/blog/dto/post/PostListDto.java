package com.portfolio.blog.dto.post;

import com.portfolio.blog.entity.File;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostListDto {
    private Long id;
    private String title;
    private String content;
    private int hit;
    private String memberName;
    private File file;

    private LocalDateTime createdDate;
}
