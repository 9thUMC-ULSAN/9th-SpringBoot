package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.dto.MemberResponseDto;

public interface MemberCommandService {
    MemberResponseDto.ReviewPreViewDto createReview(Long memberId, MemberRequestDto.ReviewDto request);
    MemberResponseDto.JoinDto signup(MemberRequestDto.JoinDto dto);
}
