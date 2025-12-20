package com.example.umc9th.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionResponseDTO {
    private Long memberMissionId;
    private Long missionId;
    private boolean isComplete;
}
