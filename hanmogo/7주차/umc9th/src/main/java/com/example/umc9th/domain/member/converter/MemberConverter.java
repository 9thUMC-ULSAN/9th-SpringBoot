package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.dto.MemberResponseDto;
import com.example.umc9th.domain.mission.MemberMission;
import com.example.umc9th.domain.review.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    // 리뷰 요청 -> Entity
    public static Review toReview(MemberRequestDto.ReviewDto request) {
        return Review.builder()
                .content(request.getContent())
                .score(request.getScore())
                .build();
    }

    // Entity -> 리뷰 응답
    public static MemberResponseDto.ReviewPreViewDto toReviewPreViewDto(Review review) {
        return MemberResponseDto.ReviewPreViewDto.builder()
                .id(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // Member -> 마이페이지 Dto
    public static MemberResponseDto.MyPageDto toMyPageDto(Member member) {
        return MemberResponseDto.MyPageDto.builder()
                .nickname(member.getName()) // ERD상 name을 닉네임으로 사용
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNum()) // 인증 여부는 로직 추가 필요, 일단 번호만
                .point(member.getPoint())
                .build();
    }

    // MemberMission Page -> 미션 리스트 Dto
    public static MemberResponseDto.MissionListDto toMissionListDto(Page<MemberMission> missionPage) {
        List<MemberResponseDto.MissionDto> list = missionPage.stream()
                .map(MemberConverter::toMissionDto)
                .collect(Collectors.toList());

        return MemberResponseDto.MissionListDto.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(list.size())
                .missionList(list)
                .build();
    }

    // MemberMission -> 단일 미션 Dto
    public static MemberResponseDto.MissionDto toMissionDto(MemberMission mm) {
        return MemberResponseDto.MissionDto.builder()
                .id(mm.getId())
                .storeName(mm.getMission().getStore().getName())
                .missionSpec(mm.getMission().getMissionSpec())
                .reward(mm.getMission().getReward())
                .deadline(mm.getMission().getDeadline())
                .status(mm.getStatus().toString())
                .build();
    }

    // 홈 화면 Dto 조립
    public static MemberResponseDto.HomeDto toHomeDto(Member member, Integer completedCount, List<MemberMission> missions) {
        List<MemberResponseDto.HomeMissionDto> missionDtos = missions.stream()
                .map(mm -> MemberResponseDto.HomeMissionDto.builder()
                        .id(mm.getMission().getId())
                        .storeName(mm.getMission().getStore().getName())
                        .storeCategory("한식")
                        .missionSpec(mm.getMission().getMissionSpec())
                        .reward(mm.getMission().getReward())
                        .dDay(ChronoUnit.DAYS.between(LocalDate.now(), mm.getMission().getDeadline()))
                        .build())
                .collect(Collectors.toList());

        return MemberResponseDto.HomeDto.builder()
                .nickname(member.getName())
                .myPoint(member.getPoint())
                .completedMissionCount(completedCount)
                .myMissionList(missionDtos)
                .build();
    }
}