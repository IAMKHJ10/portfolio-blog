package com.portfolio.blog.controller;

import com.portfolio.blog.dto.PostDto;
import com.portfolio.blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/write")
    public String postWriteForm(Model model){
        model.addAttribute("form", new PostDto());
        model.addAttribute("postList", postService.postList());
        return "post/write";
    }

    @PostMapping("/post/write")
    public String postWrite(@Valid PostDto postDto){
        postService.postWrite(postDto);
        return "redirect:/post/write";
    }

    @GetMapping("/post/{id}")
    public String postDetail(@PathVariable(name = "id") Long id, Model model){
        postService.updateHits(id);
        model.addAttribute("postDetail", postService.findById(id));
        return "post/detail";
    }

}
