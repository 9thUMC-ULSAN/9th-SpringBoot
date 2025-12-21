package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.mission.MemberMission;
import com.example.umc9th.domain.mission.Mission;
import com.example.umc9th.domain.mission.MissionStatus;
import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.store.Store;
import org.springframework.data.domain.Page;

public class MissionConverter {

    public static Mission toMission(MissionRequestDto.CreateMissionDto request, Store store) {
        return Mission.builder()
                .title(request.getTitle())
                .conditional(request.getConditional())
                .point(request.getReward())
                .deadline(request.getDeadline())
                .store(store)
                .build();
    }

    public static MissionResponseDto.CreateMissionResultDto toCreateMissionResultDto(Mission mission) {
        return MissionResponseDto.CreateMissionResultDto.builder()
                .missionId(mission.getMissionId())
                .title(mission.getTitle())
                .missionSpec(mission.getConditional())
                .reward(mission.getPoint())
                .deadline(mission.getDeadline())
                .storeName(mission.getStore().getName())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static MemberMission toMemberMission(Member member, Mission mission, MissionStatus status) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(status)
                .build();
    }

    public static MissionResponseDto.ChallengeMissionResultDto toChallengeMissionResultDto(MemberMission memberMission) {
        return MissionResponseDto.ChallengeMissionResultDto.builder()
                .memberMissionId(memberMission.getMemberMissionId())
                .missionId(memberMission.getMission().getMissionId())
                .missionTitle(memberMission.getMission().getTitle())
                .storeName(memberMission.getMission().getStore().getName())
                .status(memberMission.getStatus().name())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

    public static MissionResponseDto.StoreMissionDto toStoreMissionDto(Mission mission) {
        return MissionResponseDto.StoreMissionDto.builder()
                .missionId(mission.getMissionId())
                .title(mission.getTitle())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResponseDto.StoreMissionListDto toStoreMissionListDto(Page<Mission> missionPage) {
        return MissionResponseDto.StoreMissionListDto.builder()
                .missionList(missionPage.getContent().stream()
                        .map(MissionConverter::toStoreMissionDto)
                        .toList())
                .listSize(missionPage.getContent().size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

    public static MissionResponseDto.MyMissionDto toMyMissionDto(MemberMission memberMission) {
        return MissionResponseDto.MyMissionDto.builder()
                .memberMissionId(memberMission.getMemberMissionId())
                .missionId(memberMission.getMission().getMissionId())
                .storeName(memberMission.getMission().getStore().getName())
                .title(memberMission.getMission().getTitle())
                .conditional(memberMission.getMission().getConditional())
                .point(memberMission.getMission().getPoint())
                .deadline(memberMission.getMission().getDeadline())
                .status(memberMission.getStatus())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

    public static MissionResponseDto.MyMissionListDto toMyMissionListDto(Page<MemberMission> memberMissionPage) {
        return MissionResponseDto.MyMissionListDto.builder()
                .missionList(memberMissionPage.getContent().stream()
                        .map(MissionConverter::toMyMissionDto)
                        .toList())
                .listSize(memberMissionPage.getContent().size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }

    public static MissionResponseDto.MissionCompleteDto toMissionCompleteDto(MemberMission memberMission) {
        return MissionResponseDto.MissionCompleteDto.builder()
                .memberMissionId(memberMission.getMemberMissionId())
                .status(memberMission.getStatus())
                .updatedAt(memberMission.getUpdatedAt())
                .build();
    }
}
