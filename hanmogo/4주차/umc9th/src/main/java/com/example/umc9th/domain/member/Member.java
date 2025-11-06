package com.example.umc9th.domain.member;


import com.example.umc9th.domain.agreement.MemberAgreement;
import com.example.umc9th.domain.mission.MemberMission;
import com.example.umc9th.global.BaseTimeEntity;
import com.example.umc9th.domain.member.SocialType;
import com.example.umc9th.domain.review.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user") //
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long memberId;
    @Column(length = 50)
    private String name;

    private LocalDateTime birthdate;

    @Column(length = 20)
    private String gender;

    @Column(columnDefinition = "VARCHAR")
    private String detailAddress;

    @Column(length = 254)
    private String address;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(columnDefinition = "VARCHAR")
    private String socialUid;

    private Integer point;

    @Column(columnDefinition = "VARCHAR")
    private String email;

    @Column(length = 15)
    private String phoneNum;

    // --- 연관관계 매핑 ---

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAgreement> memberAgreementList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPreference> memberPreferenceList = new ArrayList<>();
}