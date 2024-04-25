package com.portfolio.blog.service;

import com.portfolio.blog.dto.category.CategoryListDto;
import com.portfolio.blog.dto.category.CategorySaveDto;
import com.portfolio.blog.dto.category.CategoryUpdateDto;
import com.portfolio.blog.entity.Category;
import com.portfolio.blog.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void save(CategorySaveDto dto){
        Optional<Category> lastCategory = categoryRepository.findTopByOrderByOrderNumberDesc();
        Category category = Category.builder()
                .name(dto.getName())
                .orderNumber(lastCategory.isPresent() ? lastCategory.get().getOrderNumber()+1 : 1)
                .build();
        categoryRepository.save(category);
    }

    @Transactional
    public void update(CategoryUpdateDto dto) {

        categoryRepository.orderChange(dto.getOrderNumber());

        Category category = categoryRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("선택하신 카테고리를 찾을 수 없습니다."));

        category.orderNumberUpdate(dto);
    }

    @Transactional(readOnly = true)
    public List<CategoryListDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryListDto> list = new ArrayList<>();

        for (Category category : categories){
            CategoryListDto dto = CategoryListDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
            list.add(dto);
        }
        return list;
    }

}
