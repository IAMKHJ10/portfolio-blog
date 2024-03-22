package com.portfolio.blog.controller;

import com.portfolio.blog.dto.MessageDto;
import com.portfolio.blog.dto.user.LoginDto;
import com.portfolio.blog.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
    @PostMapping("/login")
    public MessageDto<?> login(@ModelAttribute LoginDto loginDto, HttpSession session){

        MessageDto<?> result = userService.login(loginDto);
        if(result.getKey().equals("ok")){
            session.setAttribute("USER", result);
            session.setMaxInactiveInterval(60*60); // 1시간

        }

        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "user/login";
    }

}
