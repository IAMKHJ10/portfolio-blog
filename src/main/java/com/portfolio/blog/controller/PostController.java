package com.portfolio.blog.controller;

import com.portfolio.blog.dto.post.PostSaveDto;
import com.portfolio.blog.dto.post.PostUpdateDto;
import com.portfolio.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //글 쓰기 화면
    @GetMapping("/post/write")
    public String write(){
        return "post/write";
    }

    //글 쓰기
    @ResponseBody
    @PostMapping("/post/save")
    public void save(@ModelAttribute PostSaveDto dto){
        postService.save(dto);
    }

    //글 수정 화면
    @GetMapping("/post/update/{id}")
    public String update(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("post", postService.findById(id));
        return "post/update";
    }

    //글 수정
    @ResponseBody
    @PatchMapping("/post/update/{id}")
    public void update(@PathVariable(name = "id") Long id, @ModelAttribute PostUpdateDto dto){
        postService.update(id, dto);
    }

    //글 목록
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

    //글 삭제
    @ResponseBody
    @DeleteMapping("/post/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id){
        postService.delete(id);
    }

}
