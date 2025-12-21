package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.mission.MemberMission;
import com.example.umc9th.domain.mission.Mission;
import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.store.Store;

public class MissionConverter {

    public static Mission toMission(MissionRequestDto.CreateMissionDto request, Store store) {
        return Mission.builder()
                .title(request.getTitle())
                .conditional(request.getConditional())
                .missionSpec(request.getMissionSpec())
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .store(store)
                .build();
    }

    public static MissionResponseDto.CreateMissionResultDto toCreateMissionResultDto(Mission mission) {
        return MissionResponseDto.CreateMissionResultDto.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .storeName(mission.getStore().getName())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static MemberMission toMemberMission(Member member, Mission mission, String status) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(status)
                .build();
    }

    public static MissionResponseDto.ChallengeMissionResultDto toChallengeMissionResultDto(MemberMission memberMission) {
        return MissionResponseDto.ChallengeMissionResultDto.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .missionTitle(memberMission.getMission().getTitle())
                .storeName(memberMission.getMission().getStore().getName())
                .status(memberMission.getStatus())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }
}
