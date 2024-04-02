package com.portfolio.blog.dto.member;

import com.portfolio.blog.entity.File;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDetailDto {
    private Long id;
    private String uid;
    private String name;
    private String email;
    private File file;
}
