package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;

public class MissionConverter {
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();
    }

    public static MissionResponseDTO toMissionResponseDTO(MemberMission memberMission) {
        return MissionResponseDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .isComplete(memberMission.isComplete())
                .build();
    }
}
