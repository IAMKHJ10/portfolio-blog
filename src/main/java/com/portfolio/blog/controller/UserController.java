package com.portfolio.blog.controller;

import com.portfolio.blog.dto.LoginDto;
import com.portfolio.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @ResponseBody
    @PostMapping("/user/login")
    public ResponseEntity<?> login(@ModelAttribute LoginDto loginDto){
        return memberService.login(loginDto);
    }

}
