package com.portfolio.blog.controller;

import com.portfolio.blog.dto.MemberDto;
import com.portfolio.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    @PostMapping("/member/join")
    public ResponseEntity<?> save(@ModelAttribute MemberDto member){
        return memberService.save(member);
    }

}
