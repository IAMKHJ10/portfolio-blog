package com.portfolio.blog.dto.comment;

import com.portfolio.blog.entity.Member;
import com.portfolio.blog.entity.Post;
import lombok.Data;

@Data
public class CommentSaveDto {
    private String content;
    private Member member;
    private Post post;
}
