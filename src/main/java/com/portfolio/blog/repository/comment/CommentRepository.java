package com.portfolio.blog.repository.comment;

import com.portfolio.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

    Optional<Comment> findByParentId(Long parentId);

}
