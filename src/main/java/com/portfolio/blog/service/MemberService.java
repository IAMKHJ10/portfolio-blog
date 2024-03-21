package com.portfolio.blog.service;

import com.portfolio.blog.dto.member.MemberSaveDto;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.entity.common.RoleType;
import com.portfolio.blog.exception.AlreadyUseUidException;
import com.portfolio.blog.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
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
    public Member save(MemberSaveDto memberSaveDto){

        Optional<Member> member = memberRepository.findByUid(memberSaveDto.getUid());

        if(member.isPresent()){
            throw new AlreadyUseUidException();
        }

        Member newMember = Member.builder()
                .uid(memberSaveDto.getUid())
                .password(passwordEncoder.encode(memberSaveDto.getPassword())) // 비밀번호 암호화
                .name(memberSaveDto.getName())
                .email(memberSaveDto.getEmail())
                .roleType(RoleType.USER)
                .build();

        return memberRepository.save(newMember);
    }


}
