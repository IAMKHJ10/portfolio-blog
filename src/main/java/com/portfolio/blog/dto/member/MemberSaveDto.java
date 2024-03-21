package com.portfolio.blog.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberSaveDto {

    @NotBlank(message = "아이디를 입력하세요.")
    @Size(min = 4, max = 12, message = "아이디는 4~12글자 입력하세요.")
    private String uid;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min = 8, max = 16, message = "비밀번호는 8~16글자 입력하세요.")
    private String password;

    @NotBlank(message = "이름을 입력하세요.")
    @Size(min = 2, message = "이름은 2글자이상 입력하세요.")
    private String name;

    @NotBlank(message = "이메일을 입력하세요.")
    @Email
    private String email;

}
