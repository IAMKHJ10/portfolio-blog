package com.portfolio.blog.controller;

import com.portfolio.blog.dto.MemberDto;
import com.portfolio.blog.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/join")
    public String memberJoinForm(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "member/join";
    }

    @PostMapping("/member/join")
    public String memberJoin(@Valid MemberDto memberDto, BindingResult result){

        if(result.hasErrors()){
            return "member/join";
        }

        memberService.memberJoin(memberDto);

        return "redirect:/";
    }

}
