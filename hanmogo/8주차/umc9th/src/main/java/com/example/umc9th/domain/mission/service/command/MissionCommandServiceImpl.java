package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.MemberMission;
import com.example.umc9th.domain.mission.MemberMissionRepository;
import com.example.umc9th.domain.mission.Mission;
import com.example.umc9th.domain.mission.MissionRepository;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.store.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public MissionResponseDto.CreateMissionResultDto createMission(Long storeId, MissionRequestDto.CreateMissionDto request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(request, store);
        missionRepository.save(mission);

        return MissionConverter.toCreateMissionResultDto(mission);
    }

    @Override
    public MissionResponseDto.ChallengeMissionResultDto challengeMission(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        // 이미 도전 중인지 확인
        boolean alreadyChallenging = memberMissionRepository.findMyMissionsByStatus(
                memberId,
                java.util.List.of("CHALLENGING"),
                org.springframework.data.domain.PageRequest.of(0, Integer.MAX_VALUE)
        ).getContent().stream()
                .anyMatch(mm -> mm.getMission().getId().equals(missionId));

        if (alreadyChallenging) {
            throw new GeneralException(ErrorStatus.MISSION_ALREADY_CHALLENGING);
        }

        MemberMission memberMission = MissionConverter.toMemberMission(member, mission, "CHALLENGING");
        memberMissionRepository.save(memberMission);

        return MissionConverter.toChallengeMissionResultDto(memberMission);
    }
}
