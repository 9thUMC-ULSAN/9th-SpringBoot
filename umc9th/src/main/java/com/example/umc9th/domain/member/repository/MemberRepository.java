package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

//    // 회원 단건 조회
//    Optional<Member> findById(Long memberId);
//
//    // 포인트 총합
//    @Query("""
//        SELECT COALESCE(SUM(m.point), 0)
//        FROM MemberMission mm
//        JOIN mm.mission m
//        WHERE mm.member.id = :memberId
//          AND mm.isComplete = true
//    """)
//    Integer findTotalPointByMember(@Param("memberId") Long memberId);
}
