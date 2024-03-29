package com.portfolio.blog.repository.post;

import com.portfolio.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    List<Post> findAllByCategoryId(Long id);

}
