package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.MemberMission;
import com.example.umc9th.domain.mission.MemberMissionRepository;
import com.example.umc9th.domain.mission.Mission;
import com.example.umc9th.domain.mission.MissionRepository;
import com.example.umc9th.domain.mission.MissionStatus;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MissionResponseDto.StoreMissionListDto getStoreMissions(Long storeId, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<Mission> missionPage = missionRepository.findByStoreId(storeId, pageable);
        return MissionConverter.toStoreMissionListDto(missionPage);
    }

    @Override
    public MissionResponseDto.MyMissionListDto getMyChallengingMissions(Long memberId, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<MemberMission> memberMissionPage = memberMissionRepository.findByMemberIdAndStatus(
                memberId, MissionStatus.CHALLENGING, pageable);
        return MissionConverter.toMyMissionListDto(memberMissionPage);
    }
}
