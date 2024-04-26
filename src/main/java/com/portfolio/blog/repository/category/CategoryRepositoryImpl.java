package com.portfolio.blog.repository.category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.portfolio.blog.entity.QCategory.category;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void update(Long id, Long orderNumber) {
        jpaQueryFactory
                .update(category)
                .set(category.orderNumber, orderNumber)
                .where(category.id.eq(id))
                .execute();
    }
}
