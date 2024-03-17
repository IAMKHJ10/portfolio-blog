package com.portfolio.blog.controller;

import com.portfolio.blog.entity.Member;
import com.portfolio.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 사용자 등록화면
    @GetMapping("/member/join")
    public String save(){
        return "member/join";
    }

    // 사용자 등록
    @PostMapping("/member/join")
    public String save(Model model, Member member){
        model.addAttribute("saveMember", memberService.save(member));
        return "user/login";
    }

}
