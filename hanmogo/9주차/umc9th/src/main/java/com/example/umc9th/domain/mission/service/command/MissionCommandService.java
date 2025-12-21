package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;

public interface MissionCommandService {
    MissionResponseDto.CreateMissionResultDto createMission(Long storeId, MissionRequestDto.CreateMissionDto request);
    MissionResponseDto.ChallengeMissionResultDto challengeMission(Long memberId, Long missionId);
    MissionResponseDto.MissionCompleteDto completeMission(Long memberId, Long memberMissionId);
}
