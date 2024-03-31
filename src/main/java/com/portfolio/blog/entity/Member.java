package com.portfolio.blog.entity;

import com.portfolio.blog.entity.common.BaseEntity;
import com.portfolio.blog.entity.common.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@DynamicUpdate
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_uid", length = 20, nullable = false, unique = true)
    private String uid;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(name = "member_email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private RoleType roleType;

    @OneToMany(mappedBy = "member")
    private List<Post> posts;

    @OneToMany(mappedBy = "member")
    private List<File> files = new ArrayList<>();

}
