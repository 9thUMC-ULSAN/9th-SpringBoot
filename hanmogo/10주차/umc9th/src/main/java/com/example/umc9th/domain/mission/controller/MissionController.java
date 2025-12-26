package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.validation.annotation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Mission", description = "미션 관련 API")
@RestController
@RequiredArgsConstructor
@Validated
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

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

    @Operation(summary = "특정 가게의 미션 목록 조회", description = "특정 가게에 등록된 미션 목록을 페이징하여 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 페이지 번호"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "가게를 찾을 수 없음")
    })
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResponseDto.StoreMissionListDto> getStoreMissions(
            @Parameter(description = "가게 ID") @PathVariable Long storeId,
            @Parameter(description = "페이지 번호 (1 이상)") @RequestParam @ValidPage Integer page
    ) {
        return ApiResponse.onSuccess(missionQueryService.getStoreMissions(storeId, page));
    }

    @Operation(summary = "내가 진행중인 미션 목록 조회", description = "특정 회원이 현재 진행 중인 미션 목록을 페이징하여 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 페이지 번호"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    @GetMapping("/members/{memberId}/missions/challenging")
    public ApiResponse<MissionResponseDto.MyMissionListDto> getMyChallengingMissions(
            @Parameter(description = "회원 ID") @PathVariable Long memberId,
            @Parameter(description = "페이지 번호 (1 이상)") @RequestParam @ValidPage Integer page
    ) {
        return ApiResponse.onSuccess(missionQueryService.getMyChallengingMissions(memberId, page));
    }

    @Operation(summary = "진행중인 미션 완료로 변경", description = "진행 중인 미션을 완료 상태로 변경하고 변경된 미션 정보를 반환합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "진행 중인 미션이 아님"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "미션을 찾을 수 없음")
    })
    @PatchMapping("/members/{memberId}/missions/{memberMissionId}/complete")
    public ApiResponse<MissionResponseDto.MissionCompleteDto> completeMission(
            @Parameter(description = "회원 ID") @PathVariable Long memberId,
            @Parameter(description = "회원 미션 ID") @PathVariable Long memberMissionId
    ) {
        return ApiResponse.onSuccess(missionCommandService.completeMission(memberId, memberMissionId));
    }
}
