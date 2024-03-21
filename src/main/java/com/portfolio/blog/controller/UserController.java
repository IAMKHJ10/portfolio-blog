package com.portfolio.blog.controller;

import com.portfolio.blog.dto.user.LoginDto;
import com.portfolio.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @ResponseBody
    @PostMapping("/user/login")
    public UserDetails login(@ModelAttribute LoginDto loginDto){
        return userService.loadUserByUsername(loginDto.getUid());
    }

}
