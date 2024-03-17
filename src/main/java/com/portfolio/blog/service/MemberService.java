package com.portfolio.blog.service;

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
    public Member save(Member member){
        validateDuplicateMember(member); // 중복 체크
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member dupUid = memberRepository.findByUid(member.getUid());
        if(dupUid!=null){
            throw new IllegalStateException("이미 사용중인 아이디입니다.");
        }
    }

}
