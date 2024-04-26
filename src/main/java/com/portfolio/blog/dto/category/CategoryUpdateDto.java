package com.portfolio.blog.dto.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateDto {
    private Long startId;
    private Long startNum;
    private Long endId;
    private Long endNum;
}
