package com.portfolio.blog.service;

import com.portfolio.blog.dto.MessageDto;
import com.portfolio.blog.dto.member.ChangeProfileDto;
import com.portfolio.blog.dto.member.MemberDetailDto;
import com.portfolio.blog.dto.member.MemberSaveDto;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.entity.common.RoleType;
import com.portfolio.blog.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

import static com.portfolio.blog.config.SecurityConfig.passwordEncoder;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final FileService fileService;

    @Transactional
    public MessageDto<?> save(MemberSaveDto dto){

        Optional<Member> member = memberRepository.findByUid(dto.getUid());
        if(member.isPresent()) return new MessageDto<>("duplicateUid");

        Optional<Member> email = memberRepository.findByEmail(dto.getEmail());
        if(email.isPresent()) return new MessageDto<>("duplicateEmail");

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

    @Transactional
    public void changeProfile(ChangeProfileDto dto) throws IOException {
        Member member = memberRepository.findByUid(dto.getMemberUid())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if(!member.getFiles().isEmpty()){
            if(dto.getFile().isEmpty()){//등록된 이미지가 있고 업로드하는 이미지가 없을때
                fileService.delete(member.getFiles().get(0));
            }else{//등록된 이미지가 있고 업로드하는 이미지가 있을때
                fileService.delete(member.getFiles().get(0));
                fileService.saveWithProfile(dto.getFile(), member);
            }
        }else{
            if(!dto.getFile().isEmpty()){//등록된 이미지가 없고 업로드하는 이미지가 있을때
                fileService.saveWithProfile(dto.getFile(), member);
            }
        }
    }

    @Transactional(readOnly = true)
    public MemberDetailDto findByUid(String uid){
        Member member = memberRepository.findByUid(uid)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return MemberDetailDto.builder()
                .id(member.getId())
                .uid(member.getUid())
                .name(member.getName())
                .email(member.getEmail())
                .file(member.getFiles().isEmpty()?null:member.getFiles().get(0))
                .build();
    }
}
