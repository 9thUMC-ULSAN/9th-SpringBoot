package com.example.umc9th2.domain.mission.repository;

import com.example.umc9th2.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 현재 지역 기준으로 마감 안 된 미션 목록
    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "JOIN s.location l " +
            "WHERE l.name = :locationName " +
            "AND m.deadline > CURRENT_DATE")
    Page<Mission> findAvailableMissionsByLocation(
            @Param("locationName") String locationName,
            Pageable pageable
    );
}
