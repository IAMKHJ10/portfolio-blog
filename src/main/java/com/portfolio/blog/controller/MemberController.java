package com.portfolio.blog.controller;

import com.portfolio.blog.dto.member.MemberSaveDto;
import com.portfolio.blog.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 사용자 등록화면
    @GetMapping("/member/join")
    public String save(Model model){
        model.addAttribute("memberSaveDto", new MemberSaveDto());
        return "member/join";
    }

    // 사용자 등록
    @PostMapping("/member/join")
    public String save(@Valid @ModelAttribute MemberSaveDto memberSaveDto, BindingResult result, Model model){

        if(!result.hasErrors()){
            model.addAttribute("saveMember", memberService.save(memberSaveDto));
        }

        return "member/join";
    }

}
