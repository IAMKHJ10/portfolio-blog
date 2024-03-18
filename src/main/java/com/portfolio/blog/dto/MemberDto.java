package com.portfolio.blog.dto;

import com.portfolio.blog.entity.RoleType;
import lombok.Data;

@Data
public class MemberDto {
    private Long id;
    private String uid;
    private String password;
    private String name;
    private String email;
    private RoleType roleType;
}
