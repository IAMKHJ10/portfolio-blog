package com.portfolio.blog.service;

import com.portfolio.blog.dto.LoginDto;
import com.portfolio.blog.dto.MemberDto;
import com.portfolio.blog.dto.common.MessageDto;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<?> save(MemberDto memberDto){
        Member dupUid = memberRepository.findByUid(memberDto.getUid());
        if(dupUid==null){// 아이디 중복 체크
            Member newMember = Member.createMember(memberDto, passwordEncoder);
            memberRepository.save(newMember);
            return ResponseEntity.ok().body(new MessageDto<>("success", newMember));
        }else{
            return ResponseEntity.ok().body(new MessageDto<>("dup", ""));
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> login(LoginDto loginDto){
        Member member = memberRepository.findByUid(loginDto.getUid());
        if(member==null){
            return ResponseEntity.ok().body(new MessageDto<>("noMember", ""));
        }

        if(passwordEncoder.matches(loginDto.getPassword(), member.getPassword())){
            return ResponseEntity.ok().body(new MessageDto<>("success", ""));
        }else {
            return ResponseEntity.ok().body(new MessageDto<>("differPw", ""));
        }

    }

}
