package com.portfolio.blog.service;

import com.portfolio.blog.dto.user.LoginDto;
import com.portfolio.blog.dto.member.MemberSaveDto;
import com.portfolio.blog.dto.common.MessageDto;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.entity.RoleType;
import com.portfolio.blog.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<?> save(MemberSaveDto memberSaveDto){

        Optional<Member> member = memberRepository.findByUid(memberSaveDto.getUid());

        if(member.isEmpty()){ // 회원 중복 체크
            Member newMember = Member.builder()
                    .uid(memberSaveDto.getUid())
                    .password(passwordEncoder.encode(memberSaveDto.getPassword())) // 비밀번호 암호화
                    .name(memberSaveDto.getName())
                    .email(memberSaveDto.getEmail())
                    .roleType(RoleType.USER)
                    .build();

            memberRepository.save(newMember);

            return ResponseEntity.ok().body(new MessageDto<>("success", newMember));
        }else{
            return ResponseEntity.ok().body(new MessageDto<>("dup", ""));
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> login(LoginDto loginDto){

        Optional<Member> member = memberRepository.findByUid(loginDto.getUid());
        Member memberEntity = member.orElse(null);

        if(member.isEmpty()){ // 회원 유무 체크
            return ResponseEntity.ok().body(new MessageDto<>("noMember", ""));
        }

        if (passwordEncoder.matches(loginDto.getPassword(), memberEntity.getPassword())) { // 비밀번호 체크
            return ResponseEntity.ok().body(new MessageDto<>("success", ""));
        } else {
            return ResponseEntity.ok().body(new MessageDto<>("differPw", ""));
        }

    }

}
