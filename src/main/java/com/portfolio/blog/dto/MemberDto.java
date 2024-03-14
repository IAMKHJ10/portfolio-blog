package com.portfolio.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberDto {

    private Long id;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 3, max = 10, message = "아이디는 3~10자만 가능합니다.")
    private String uid;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 3, max = 10, message = "비밀번호는 3~10자만 가능합니다.")
    private String password;

    private String name;

    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

}
