package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.dto.MemberResponseDto;
import com.example.umc9th.domain.member.service.command.MemberCommandService;
import com.example.umc9th.domain.member.service.query.MemberQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    // 1. 리뷰 작성 API
    @PostMapping("/{memberId}/reviews")
    public ApiResponse<MemberResponseDto.ReviewPreViewDto> createReview(
            @PathVariable Long memberId,
            @RequestBody @Valid MemberRequestDto.ReviewDto request) {

        MemberResponseDto.ReviewPreViewDto result = memberCommandService.createReview(memberId, request);
        return ApiResponse.onSuccess(result);
    }

    // 2. 마이페이지 조회 API
    @GetMapping("/{memberId}/mypage")
    public ApiResponse<MemberResponseDto.MyPageDto> getMyPage(@PathVariable Long memberId) {
        MemberResponseDto.MyPageDto result = memberQueryService.getMyPage(memberId);
        return ApiResponse.onSuccess(result);
    }

    // 3. 미션 목록 API (진행중/완료)
    // 예: /members/1/missions?status=CHALLENGING&page=1
    @GetMapping("/{memberId}/missions")
    public ApiResponse<MemberResponseDto.MissionListDto> getMyMissions(
            @PathVariable Long memberId,
            @RequestParam(name = "status") String status,
            @RequestParam(name = "page") Integer page) {

        // 페이지 번호는 0부터 시작하므로 -1 처리 (프론트가 1부터 보낸다고 가정 시)
        MemberResponseDto.MissionListDto result = memberQueryService.getMyMissionList(memberId, status, page - 1);
        return ApiResponse.onSuccess(result);
    }

    // 4. 홈 화면 조회 API
    @GetMapping("/{memberId}/home")
    public ApiResponse<MemberResponseDto.HomeDto> getHome(@PathVariable Long memberId) {
        MemberResponseDto.HomeDto result = memberQueryService.getHomeScreen(memberId);
        return ApiResponse.onSuccess(result);
    }
}