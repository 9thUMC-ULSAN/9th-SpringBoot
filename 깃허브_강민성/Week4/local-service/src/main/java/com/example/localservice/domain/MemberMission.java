package com.example.localservice.domain;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Builder
@Table(name = "member_mission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMissionId; // PK 이름 member_mission_id 반영

    @Column(nullable = false)
    private Boolean isComplete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;
}