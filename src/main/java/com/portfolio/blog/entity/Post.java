package com.portfolio.blog.entity;

import com.portfolio.blog.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    private int hit=0;

    public void update(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }

}
