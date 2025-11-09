package com.example.localservice.repository;
import com.example.localservice.domain.Member;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // (1) 이메일로 사용자 정보(닉네임, 이메일, 포인트 등) 조회
    // 필드 이름으로 자동 생성 쿼리: findBy{필드명}
    Optional<Member> findByEmail(String email);

    // (2) 소셜 UID로 사용자 정보 조회 (로그인 시 사용)
    Optional<Member> findBySocialUid(String socialUid);
}