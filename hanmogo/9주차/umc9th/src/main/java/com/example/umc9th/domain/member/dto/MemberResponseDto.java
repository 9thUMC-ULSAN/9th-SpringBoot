package com.example.umc9th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDto {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinDto {
        private Long id;
        private String name;
        private LocalDateTime createAt;
    }

    // 1. 리뷰 작성 완료 응답
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDto {
        private Long id;
        private LocalDateTime createdAt;
    }

    // 2. 마이페이지 화면 응답
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPageDto {
        private String nickname;
        private String email;
        private String phoneNumber;
        private Integer point;
    }

    // 3. 미션 목록 (페이징 포함) 응답
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListDto {
        private List<MissionDto> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDto {
        private Long id;
        private String storeName;
        private String missionSpec; // "12,000원 이상 식사 시"
        private Integer reward;     // 500P
        private LocalDate deadline;
        private String status;      // CHALLENGING, COMPLETE
    }

    // 4. 홈 화면 응답
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeDto {
        private String nickname;
        private Integer myPoint;
        private Integer completedMissionCount; // 7 (달성한 미션 수)
        private List<HomeMissionDto> myMissionList; // 하단 "MY MISSION" 리스트
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeMissionDto {
        private Long id;
        private String storeName;
        private String storeCategory; // 중식당
        private String missionSpec;
        private Integer reward;
        private Long dDay;
    }
}