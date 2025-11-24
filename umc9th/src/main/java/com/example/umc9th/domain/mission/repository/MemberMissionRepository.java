package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 특정 회원의 모든 미션 조회
    List<MemberMission> findByMember_Id(Long memberId);

    // 진행 중 미션만
    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.isComplete = false")
    List<MemberMission> findOngoingMissions(@Param("memberId") Long memberId);

    // 완료된 미션만
    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.isComplete = true")
    List<MemberMission> findCompletedMissions(@Param("memberId") Long memberId);

    // 회원의 특정 지역에서 완료한 미션 수
    @Query("""
        SELECT COUNT(mm)
        FROM MemberMission mm
        JOIN mm.mission m
        JOIN m.store s
        JOIN s.location l
        WHERE mm.member.id = :memberId
          AND l.id = :locationId
          AND mm.isComplete = true
    """)
    int countCompletedMissionsByLocation(@Param("memberId") Long memberId,
                                         @Param("locationId") Long locationId);
}
