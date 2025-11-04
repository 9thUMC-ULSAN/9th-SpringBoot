package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 🔹 특정 지역(location.name)에 속한 미션 조회
    @Query("SELECT m FROM Mission m JOIN m.store s JOIN s.location l WHERE l.name = :locationName")
    List<Mission> findMissionsByLocation(@Param("locationName") String locationName);

    // 🔹 특정 가게(store.id)에 등록된 미션 목록
    @Query("SELECT m FROM Mission m JOIN m.store s WHERE s.id = :storeId")
    List<Mission> findMissionsByStoreId(@Param("storeId") Long storeId);

    // 🔹 포인트가 일정 기준 이상인 미션 조회
    @Query("SELECT m FROM Mission m WHERE m.point >= :minPoint ORDER BY m.point DESC")
    List<Mission> findHighPointMissions(@Param("minPoint") int minPoint);
}
