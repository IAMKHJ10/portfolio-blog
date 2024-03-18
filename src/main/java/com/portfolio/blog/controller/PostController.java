package com.portfolio.blog.controller;

import com.portfolio.blog.dto.PostDto;
import com.portfolio.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String write(){
        return "post/write";
    }

    //글쓰기
    @ResponseBody
    @PostMapping("/post/write")
    public ResponseEntity<?> write(@ModelAttribute PostDto postDto){
        return postService.save(postDto);
    }

    //글수정 화면
    @GetMapping("/post/update/{id}")
    public String update(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("postDetail", postService.findById(id));
        return "post/update";
    }

    //글수정
    @PostMapping("/post/update")
    @ResponseBody
    public String update(@ModelAttribute PostDto postDto){
        postService.update(postDto);
        return "redirect:/post/update/"+postDto.getId();
    }

    //글 단건 조회
    @GetMapping("/post/{id}")
    public String findById(@PathVariable(name = "id") Long id, Model model){
        postService.updateHits(id);
        model.addAttribute("postDetail", postService.findById(id));
        return "post/detail";
    }


}
