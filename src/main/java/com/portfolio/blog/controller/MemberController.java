package com.portfolio.blog.controller;

import com.portfolio.blog.dto.MemberDto;
import com.portfolio.blog.repository.member.MemberRepository;
import com.portfolio.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/join")
    public String memberJoinForm(){
        return "member/join";
    }

    @PostMapping("/member/join")
    public void memberJoin(MemberDto memberDto){
        memberService.memberJoin(memberDto);
    }

}
