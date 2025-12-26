package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.store.dto.StoreRequestDto;
import com.example.umc9th.domain.store.dto.StoreResponseDto;
import com.example.umc9th.domain.store.service.command.StoreCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
public class StoreController {

    private final StoreCommandService storeCommandService;

    // 특정 지역에 가게 추가
    @PostMapping("/{regionId}/stores")
    public ApiResponse<StoreResponseDto.CreateStoreResultDto> createStore(
            @PathVariable Integer regionId,
            @RequestBody StoreRequestDto.CreateStoreDto request) {

        StoreResponseDto.CreateStoreResultDto result = storeCommandService.createStore(regionId, request);
        return ApiResponse.onSuccess(result);
    }
}
