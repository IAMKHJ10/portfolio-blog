package com.portfolio.blog.service;

import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        Member member = memberRepository.findByUid(uid)
                .orElseThrow(() -> new IllegalArgumentException("ㅇㅇㅇㅇ"));

        return User.builder()
                .username(member.getUid())
                .password(member.getPassword())
                .build();
    }
/*
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
 */
}
