package com.example.umc9th.domain.mission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    //특정 지역(Region)의 가게(Store) 미션 목록 조회
    @Query(value = "SELECT m FROM Mission m JOIN m.store s JOIN s.region r WHERE r.regionId = :regionId",
            countQuery = "SELECT COUNT(m) FROM Mission m JOIN m.store s JOIN s.region r WHERE r.regionId = :regionId")
    Page<Mission> findMissionsByRegionId(@Param("regionId") Integer regionId, Pageable pageable);

    // 특정 가게의 미션 목록 조회
    @Query("SELECT m FROM Mission m WHERE m.store.storeId = :storeId")
    Page<Mission> findByStoreId(@Param("storeId") Long storeId, Pageable pageable);
}


