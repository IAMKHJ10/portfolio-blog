package com.portfolio.blog.controller;

import com.portfolio.blog.dto.MessageDto;
import com.portfolio.blog.dto.post.PostSaveDto;
import com.portfolio.blog.dto.post.PostUpdateDto;
import com.portfolio.blog.service.CategoryService;
import com.portfolio.blog.service.CommentService;
import com.portfolio.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final CategoryService categoryService;

    //글 쓰기 화면
    @GetMapping("/post/write")
    public String write(Model model){
        model.addAttribute("categoryList", categoryService.findAll());
        return "post/write";
    }

    //글 쓰기
    @ResponseBody
    @PostMapping("/post/save")
    public MessageDto<?> save(@ModelAttribute PostSaveDto dto){
        return postService.save(dto);
    }

    //글 수정 화면
    @GetMapping("/post/update/{id}")
    public String update(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("categoryList", categoryService.findAll());
        return "post/update";
    }

    //글 수정
    @ResponseBody
    @PatchMapping("/post/update/{id}")
    public MessageDto<?> update(@PathVariable(name = "id") Long id, @ModelAttribute PostUpdateDto dto){
        return postService.update(id, dto);
    }

    //글 목록
    @GetMapping("/post/list")
    public String list(Model model){
        model.addAttribute("categoryList", categoryService.findAll());
        model.addAttribute("list", postService.findAll());
        return "post/list";
    }

    //글 단건 조회
    @GetMapping("/post/detail/{id}")
    public String findById(@PathVariable(name = "id") Long id, Model model){
        postService.updateHits(id);
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("commentList", commentService.findAllByPost(id));
        return "post/detail";
    }

    //글 삭제
    @ResponseBody
    @DeleteMapping("/post/delete/{id}")
    public MessageDto<?> delete(@PathVariable(name = "id") Long id){
        return postService.delete(id);
    }

}
