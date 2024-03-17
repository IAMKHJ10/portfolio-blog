package com.portfolio.blog.repository.member;

import com.portfolio.blog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Member findByUid(String uid);

}
