package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.dto.MemberResponseDto;

public interface MemberQueryService {
    MemberResponseDto.MyPageDto getMyPage(Long memberId);
    MemberResponseDto.MissionListDto getMyMissionList(Long memberId, String status, Integer page);
    MemberResponseDto.HomeDto getHomeScreen(Long memberId);
}