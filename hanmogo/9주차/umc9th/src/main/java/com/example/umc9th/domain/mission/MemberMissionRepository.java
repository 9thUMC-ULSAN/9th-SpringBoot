package com.example.umc9th.domain.mission;

import com.example.umc9th.domain.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query(value = "SELECT mm FROM MemberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store s " +
            "WHERE mm.member.memberId = :memberId AND mm.status IN :statuses",
            countQuery = "SELECT COUNT(mm) FROM MemberMission mm " +
                    "WHERE mm.member.memberId = :memberId AND mm.status IN :statuses")
    Page<MemberMission> findMyMissionsByStatus(
            @Param("memberId") Long memberId,
            @Param("statuses") List<MissionStatus> statuses,
            Pageable pageable
    );

    @Query("SELECT COUNT(mm) FROM MemberMission mm " +
            "WHERE mm.member.memberId = :memberId AND mm.status = :status")
    Integer countByMemberAndStatus(@Param("memberId") Long memberId, @Param("status") MissionStatus status);

    @Query("SELECT mm FROM MemberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store s " +
            "WHERE mm.member.memberId = :memberId AND mm.status = :status " +
            "ORDER BY mm.createdAt DESC")
    List<MemberMission> findRecentMissionsByStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status, Pageable pageable);

    @Query("SELECT mm FROM MemberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store s " +
            "WHERE mm.member.memberId = :memberId AND mm.status = :status")
    Page<MemberMission> findByMemberIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    Optional<MemberMission> findByMemberMissionIdAndMemberMemberId(Long memberMissionId, Long memberId);
}

