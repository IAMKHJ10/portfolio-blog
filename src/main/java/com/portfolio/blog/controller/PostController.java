package com.portfolio.blog.controller;

import com.portfolio.blog.dto.post.PostSaveDto;
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

    //글쓰기 화면
    @GetMapping("/post/write")
    public String write(){
        return "post/write";
    }

    //글쓰기
    @ResponseBody
    @PostMapping("/post/write")
    public ResponseEntity<?> write(@ModelAttribute PostSaveDto dto){
        return postService.save(dto);
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
    public String update(@ModelAttribute PostSaveDto dto){
        postService.update(dto);
        return "redirect:/post/update/"+dto.getId();
    }

    //글목록
    @GetMapping("/post/list")
    public String list(Model model){
        model.addAttribute("list", postService.findAll());
        return "post/list";
    }

    //글 단건 조회
    @GetMapping("/post/{id}")
    public String findById(@PathVariable(name = "id") Long id, Model model){
        postService.updateHits(id);
        model.addAttribute("post", postService.findById(id));
        return "post/detail";
    }


}
