package com.portfolio.blog.entity;

import com.portfolio.blog.dto.MemberDto;
import com.portfolio.blog.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String uid;

    private String password;

    @Column(nullable = false)
    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;

    @Builder
    public Member(String uid, String password, String name, String email, RoleType roleType) {
        this.uid = uid;
        this.password = password;
        this.name = name;
        this.email = email;
        this.roleType = roleType;
    }

    public static Member createMember(MemberDto dto, PasswordEncoder passwordEncoder){
        return Member.builder()
                .uid(dto.getUid())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .email(dto.getEmail())
                .roleType(RoleType.USER)
                .build();
    }
}
