package com.portfolio.blog.service;

import com.portfolio.blog.dto.MemberDto;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void memberJoin(MemberDto memberDto){
        Member findMember = memberRepository.findByName(memberDto.getUid())
                .orElseThrow(RuntimeException::new);
    }

}
