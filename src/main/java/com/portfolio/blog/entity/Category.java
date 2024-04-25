package com.portfolio.blog.entity;

import com.portfolio.blog.dto.category.CategoryUpdateDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@DynamicUpdate
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String name;

    @Column(name = "order_number")
    private Long orderNumber;

    public void orderNumberUpdate(CategoryUpdateDto dto) {
        this.orderNumber = dto.getOrderNumber();
    }

}
