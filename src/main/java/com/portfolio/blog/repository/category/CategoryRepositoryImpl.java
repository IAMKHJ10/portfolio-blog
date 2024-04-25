package com.portfolio.blog.repository.category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.portfolio.blog.entity.QCategory.category;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void orderChange(Long orderNumber) {
        jpaQueryFactory
                .update(category)
                .set(category.orderNumber, category.orderNumber.add(1))
                .where(category.orderNumber.goe(orderNumber))
                .execute();
    }
}
