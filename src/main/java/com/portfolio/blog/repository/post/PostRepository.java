package com.portfolio.blog.repository.post;

import com.portfolio.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
    Page<Post> findByTitleContainingOrContentContaining(String keyword, String keyword2, Pageable pageable);
}
