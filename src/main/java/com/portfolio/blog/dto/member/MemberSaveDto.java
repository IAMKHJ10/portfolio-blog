package com.portfolio.blog.dto.member;

import com.portfolio.blog.entity.RoleType;
import lombok.Data;

@Data
public class MemberSaveDto {
    private String uid;
    private String password;
    private String name;
    private String email;
    private RoleType roleType;
}
