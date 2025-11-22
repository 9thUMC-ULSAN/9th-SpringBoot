package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 특정 지역의 미션 목록
    List<Mission> findByStore_Location_Name(String locationName);

    // 특정 가게의 미션 목록
    @Query("SELECT m FROM Mission m WHERE m.store.id = :storeId")
    List<Mission> findMissionsByStoreId(@Param("storeId") Long storeId);

    // 특정 지역에서 아직 완료되지 않은 미션들
    @Query("""
        SELECT m FROM Mission m
        JOIN m.store s
        JOIN s.location l
        WHERE l.id = :locationId
          AND (m.deadline IS NULL OR m.deadline > CURRENT_DATE)
          AND NOT EXISTS (
              SELECT 1 FROM MemberMission mm
              WHERE mm.mission.id = m.id
              AND mm.member.id = :memberId
          )
        ORDER BY COALESCE(m.createdAt, m.deadline) DESC
    """)
    List<Mission> findAvailableMissions(@Param("locationId") Long locationId,
                                        @Param("memberId") Long memberId);
}
