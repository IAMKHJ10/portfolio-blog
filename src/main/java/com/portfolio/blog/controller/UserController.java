package com.portfolio.blog.controller;

import com.portfolio.blog.dto.MessageDto;
import com.portfolio.blog.dto.user.LoginDto;
import com.portfolio.blog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request){

        if(request.getSession().getAttribute("USER")!=null){
            return "redirect:/";
        }

        request.getSession().setAttribute("prevPage", request.getHeader("Referer"));

        return "user/login";
    }

    @ResponseBody
    @PostMapping("/login")
    public MessageDto<?> login(@ModelAttribute LoginDto loginDto, HttpServletRequest request){

        MessageDto<?> result = userService.login(loginDto);

        if(result.getKey().equals("ok")){
            request.getSession().setAttribute("USER", result.getData());
            request.getSession().setMaxInactiveInterval(60*60); // 1시간
        }

        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/user/login";
    }

    @GetMapping("/myPage/profile")
    public String profile(){

        return "user/profile";
    }

}
