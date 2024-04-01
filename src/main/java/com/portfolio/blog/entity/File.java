package com.portfolio.blog.entity;

import com.portfolio.blog.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@DynamicUpdate
public class File extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String originFileName;

    @Column(nullable = false)
    private String filePath ;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false)
    private Long fileSize;

}
