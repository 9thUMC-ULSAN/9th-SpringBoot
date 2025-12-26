package com.example.umc9th.domain.mission;

import com.example.umc9th.domain.member.Member;
import com.example.umc9th.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user_mission")
public class MemberMission extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id")
    private Long memberMissionId;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private MissionStatus status;

    // --- 연관관계 매핑 ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public void completeMission() {
        this.status = MissionStatus.COMPLETE;
    }
}