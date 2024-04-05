package com.portfolio.blog.service;

import com.portfolio.blog.dto.MessageDto;
import com.portfolio.blog.dto.user.LoginDto;
import com.portfolio.blog.dto.user.LoginSessionDto;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.portfolio.blog.config.SecurityConfig.passwordEncoder;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MemberRepository memberRepository;

    @Transactional
    public MessageDto<?> login(LoginDto dto){

        Optional<Member> member = memberRepository.findByUid(dto.getUid());

        if(member.isEmpty()){ // 회원 없으면
            return new MessageDto<>("noMember");
        }

        if(passwordEncoder().matches(dto.getPassword(), member.get().getPassword())){ // 비밀번호 맞으면
            LoginSessionDto loginMember = LoginSessionDto.builder()
                    .id(member.get().getId())
                    .uid(member.get().getUid())
                    .name(member.get().getName())
                    .email(member.get().getEmail())
                    .roleType(member.get().getRoleType())
                    .files(member.get().getFiles())
                    .build();
            return new MessageDto<>("ok", loginMember);
        }

        return new MessageDto<>("differPw");
    }

}
