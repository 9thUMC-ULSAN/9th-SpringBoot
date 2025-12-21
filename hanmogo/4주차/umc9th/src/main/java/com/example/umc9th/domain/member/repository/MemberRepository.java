package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 회원 정보 + 작성한 리뷰 목록까지 한 번에 가져올 때
    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.reviewList r WHERE m.id = :memberId")
    Optional<Member> findWithReviewsById_Query(@Param("memberId") Long memberId);
}
