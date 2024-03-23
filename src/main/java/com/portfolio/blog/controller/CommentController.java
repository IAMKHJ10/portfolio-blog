package com.portfolio.blog.controller;

import com.portfolio.blog.dto.MessageDto;
import com.portfolio.blog.dto.comment.CommentSaveDto;
import com.portfolio.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 등록
    @ResponseBody
    @PostMapping("/comment/save")
    public MessageDto<?> save(@ModelAttribute CommentSaveDto dto){
        return commentService.save(dto);
    }

    @ResponseBody
    @PostMapping("/reply/save")
    public MessageDto<?> replySave(@ModelAttribute CommentSaveDto dto){
        return commentService.save(dto);
    }

}
