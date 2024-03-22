package com.portfolio.blog.service;

import com.portfolio.blog.dto.MessageDto;
import com.portfolio.blog.dto.member.MemberSaveDto;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.entity.common.RoleType;
import com.portfolio.blog.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.portfolio.blog.config.SecurityConfig.passwordEncoder;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MessageDto<?> save(MemberSaveDto dto){

        Optional<Member> member = memberRepository.findByUid(dto.getUid());

        if(member.isPresent()){
            return new MessageDto<>("duplicate");
        }

        Member newMember = Member.builder()
                .uid(dto.getUid())
                .password(passwordEncoder().encode(dto.getPassword())) // 비밀번호 암호화
                .name(dto.getName())
                .email(dto.getEmail())
                .roleType(RoleType.USER)
                .build();

        memberRepository.save(newMember);

        return new MessageDto<>("ok", newMember.getName());
    }

}
