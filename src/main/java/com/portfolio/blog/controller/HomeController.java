package com.portfolio.blog.controller;

import com.portfolio.blog.dto.post.PostListDto;
import com.portfolio.blog.service.CategoryService;
import com.portfolio.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("categoryList", categoryService.findAll());
        return "main";
    }

    @ResponseBody
    @GetMapping("/post/scroll")
    public Slice<PostListDto> list(@PageableDefault(page = 1, size = 12, direction = Sort.Direction.DESC) Pageable pageable){
        return postService.findAllSlice(pageable);
    }

}
