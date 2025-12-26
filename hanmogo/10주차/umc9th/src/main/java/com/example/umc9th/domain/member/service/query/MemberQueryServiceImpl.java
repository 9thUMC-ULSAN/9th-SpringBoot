package com.example.umc9th.domain.member.service.query;


import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberResponseDto;
import com.example.umc9th.domain.mission.MemberMission;
import com.example.umc9th.domain.mission.MemberMissionRepository;
import com.example.umc9th.domain.mission.MissionStatus;
import com.example.umc9th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 1. 마이페이지
    @Override
    public MemberResponseDto.MyPageDto getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        return MemberConverter.toMyPageDto(member);
    }

    // 2. 미션 목록 (페이징)
    @Override
    public MemberResponseDto.MissionListDto getMyMissionList(Long memberId, String status, Integer page) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        MissionStatus missionStatus = MissionStatus.valueOf(status);
        List<MissionStatus> statusList = List.of(missionStatus);

        Page<MemberMission> missionPage = memberMissionRepository.findMyMissionsByStatus(
                memberId,
                statusList,
                PageRequest.of(page, 10)
        );
        return MemberConverter.toMissionListDto(missionPage);
    }

    // 3. 홈 화면 조회
    @Override
    public MemberResponseDto.HomeDto getHomeScreen(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        // 1) 완료된 미션 개수 조회 ("COMPLETE" 상태라고 가정)
        Integer completedCount = memberMissionRepository.countByMemberAndStatus(memberId, MissionStatus.COMPLETE);

        // 2) 진행 중인 미션 최신순 5개 조회 ("CHALLENGING" 상태, PageRequest로 5개 제한)
        List<MemberMission> recentMissions = memberMissionRepository.findRecentMissionsByStatus(
                memberId,
                MissionStatus.CHALLENGING,
                PageRequest.of(0, 5)
        );

        return MemberConverter.toHomeDto(member, completedCount, recentMissions);
    }
}