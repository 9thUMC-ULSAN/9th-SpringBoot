package com.example.umc9th.domain.store.service.command;

import com.example.umc9th.domain.store.dto.StoreRequestDto;
import com.example.umc9th.domain.store.dto.StoreResponseDto;

public interface StoreCommandService {
    StoreResponseDto.CreateStoreResultDto createStore(Integer regionId, StoreRequestDto.CreateStoreDto request);
}
