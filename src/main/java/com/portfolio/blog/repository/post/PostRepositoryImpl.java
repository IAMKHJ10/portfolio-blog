package com.portfolio.blog.repository.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.portfolio.blog.entity.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void updateHits(Long id) {
        jpaQueryFactory
                .update(post)
                .set(post.hit, post.hit.add(1))
                .where(post.id.eq(id))
                .execute();
    }

}
