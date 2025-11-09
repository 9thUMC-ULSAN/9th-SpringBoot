package com.example.localservice.repository;
import com.example.localservice.domain.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m " +
            // 특정 지역 (Location ID)의 가게에 속한 미션만 선택
            "JOIN m.store s WHERE s.location.locationId = :locationId " +
            // 현재 사용자가 아직 진행 중(isComplete=true 또는 false) 또는 완료된 미션에 포함되지 않은 미션만 선택
            "AND m.missionId NOT IN ( " +
            "  SELECT mm.mission.missionId FROM MemberMission mm WHERE mm.member.memberId = :memberId " +
            ") ORDER BY m.created_at DESC")
    Page<Mission> findAvailableMissionsByLocationAndMember(
            @Param("locationId") Long locationId,
            @Param("memberId") Long memberId,
            Pageable pageable);
}