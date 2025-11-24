package com.example.umc9th2.domain.member.repository;

import com.example.umc9th2.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 회원 정보 + 리뷰 목록 함께 조회 (MyPage)
    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.reviewList r WHERE m.id = :memberId")
    Optional<Member> findWithReviewsById(@Param("memberId") Long memberId);
}
