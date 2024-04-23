package com.portfolio.blog.controller;

import com.portfolio.blog.dto.category.CategorySaveDto;
import com.portfolio.blog.dto.category.CategoryUpdateDto;
import com.portfolio.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 저장
    @PostMapping("/category/save")
    public String save(@ModelAttribute CategorySaveDto dto, Model model){
        categoryService.save(dto);
        model.addAttribute("categoryList", categoryService.findAll());
        return "user/category :: #cate";
    }

    // 카테고리 수정
    @PostMapping("/category/update")
    public String update(@ModelAttribute CategoryUpdateDto dto, Model model){
        categoryService.update(dto);
        model.addAttribute("categoryList", categoryService.findAll());
        return "user/category :: #cate";
    }

}
