package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.MissionResponseDto;

public interface MissionQueryService {
    MissionResponseDto.StoreMissionListDto getStoreMissions(Long storeId, Integer page);
    MissionResponseDto.MyMissionListDto getMyChallengingMissions(Long memberId, Integer page);
}
