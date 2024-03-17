package com.portfolio.blog.controller;

import com.portfolio.blog.entity.Post;
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

    //글목록
    @GetMapping("/post/list")
    public String list(Model model){
        model.addAttribute("postList", postService.findAll());
        return "post/list";
    }

    //글쓰기 화면
    @GetMapping("/post/write")
    public String write(Model model){
        model.addAttribute("postList", postService.findAll());
        return "post/write";
    }

    //글쓰기
    @PostMapping("/post/write")
    public String write(@Valid Post post){
        postService.save(post);
        return "redirect:/post/list";
    }


    //글수정 화면
    @GetMapping("/post/update/{id}")
    public String update(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("postDetail", postService.findById(id));
        return "post/update";
    }

    //글수정
    @PostMapping("/post/update")
    public String update(@Valid Post post){
        postService.update(post);
        return "redirect:/post/update/"+post.getId();
    }

    //글 단건 조회
    @GetMapping("/post/{id}")
    public String findById(@PathVariable(name = "id") Long id, Model model){
        postService.updateHits(id);
        model.addAttribute("postDetail", postService.findById(id));
        return "post/detail";
    }


}
