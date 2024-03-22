package com.portfolio.blog.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSaveDto {
    private String uid;
    private String password;
    private String name;
    private String email;
}
