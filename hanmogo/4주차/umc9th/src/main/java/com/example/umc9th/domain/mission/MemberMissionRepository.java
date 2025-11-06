package com.example.umc9th.domain.mission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {


    //진행중, 진행 완료한 미션 모아서 조회
    @Query(value = "SELECT mm FROM MemberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store s " +
            "WHERE mm.member.memberId = :memberId AND mm.status IN :statuses",
            countQuery = "SELECT COUNT(mm) FROM MemberMission mm " +
                    "WHERE mm.member.memberId = :memberId AND mm.status IN :statuses")
    Page<MemberMission> findMyMissionsByStatus(
            @Param("memberId") Long memberId,
            @Param("statuses") List<String> statuses,
            Pageable pageable
    );
}
