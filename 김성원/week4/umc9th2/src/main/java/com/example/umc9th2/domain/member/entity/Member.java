package com.example.umc9th2.domain.member.entity;

import com.example.umc9th2.domain.member.entity.mapping.MemberFood;
import com.example.umc9th2.domain.member.entity.mapping.MemberTerm;
import com.example.umc9th2.domain.member.enums.Gender;
import com.example.umc9th2.domain.member.enums.SocialType;
import com.example.umc9th2.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th2.domain.review.entity.Review;
import com.example.umc9th2.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birth;

    @Column(length = 255)
    private String address;

    @Column(length = 255)
    private String detail_address;

    @Enumerated(EnumType.STRING)
    private SocialType social_type;

    @Column(length = 255)
    private String social_uid;

    @Column(length = 255)
    private String email;

    @Column(length = 255)
    private String phone_number;

    private Integer point;

    private LocalDateTime deleted_at;

    // 양방향 매핑
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}