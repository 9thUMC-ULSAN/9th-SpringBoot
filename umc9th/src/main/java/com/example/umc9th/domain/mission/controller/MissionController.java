package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionResponseDTO> challengeMission(
            @PathVariable Long missionId
    ) {
        MissionResponseDTO response =
                missionService.challengeMission(missionId);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                response
        );
    }
}
