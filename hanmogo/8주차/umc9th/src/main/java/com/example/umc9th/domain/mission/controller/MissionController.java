package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionCommandService missionCommandService;

    // 가게에 미션 추가
    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResponseDto.CreateMissionResultDto> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionRequestDto.CreateMissionDto request) {

        MissionResponseDto.CreateMissionResultDto result = missionCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(result);
    }

    // 미션 도전하기
    @PostMapping("/members/{memberId}/missions/{missionId}/challenge")
    public ApiResponse<MissionResponseDto.ChallengeMissionResultDto> challengeMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId) {

        MissionResponseDto.ChallengeMissionResultDto result = missionCommandService.challengeMission(memberId, missionId);
        return ApiResponse.onSuccess(result);
    }
}
