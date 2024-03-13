package com.portfolio.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @GetMapping("/post/write")
    public String postWriteForm(){
        return "post/write";
    }

    @PostMapping("/post/write")
    public void postWrite(){
    }
}
