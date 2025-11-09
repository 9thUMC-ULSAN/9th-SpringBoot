package com.example.localservice.repository;
import com.example.localservice.domain.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 사용자의 모든 미션 수행 이력을 상태별로 페이징하여 조회
    // user_id는 Member 엔티티를 참조하므로, 필드명 'member'를 이용해 쿼리 생성
    // isComplete는 Boolean 타입 필드
    Page<MemberMission> findAllByMember_MemberIdAndIsComplete(Long memberId, Boolean isComplete, Pageable pageable);

    // 모든 미션 이력을 최신순으로 페이징하여 조회
    Page<MemberMission> findAllByMember_MemberIdOrderByMemberMissionIdDesc(Long memberId, Pageable pageable);
}