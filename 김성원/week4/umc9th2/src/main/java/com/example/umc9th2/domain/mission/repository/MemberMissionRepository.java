package com.example.umc9th2.domain.mission.repository;

import com.example.umc9th2.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 진행 상태(완료/미완료)별로 미션 조회
    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId ")
    Page<MemberMission> findByMemberAndStatus(
            @Param("memberId") Long memberId,
            @Param("isComplete") Boolean isComplete,
            Pageable pageable
    );
}
