package com.example.umc9th.domain.store.converter;

import com.example.umc9th.domain.region.Region;
import com.example.umc9th.domain.store.Store;
import com.example.umc9th.domain.store.dto.StoreRequestDto;
import com.example.umc9th.domain.store.dto.StoreResponseDto;

public class StoreConverter {

    public static Store toStore(StoreRequestDto.CreateStoreDto request, Region region) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .managerNumber(request.getManagerNumber())
                .region(region)
                .build();
    }

    public static StoreResponseDto.CreateStoreResultDto toCreateStoreResultDto(Store store) {
        return StoreResponseDto.CreateStoreResultDto.builder()
                .storeId(store.getStoreId())
                .name(store.getName())
                .address(store.getAddress())
                .regionName(store.getRegion().getName())
                .createdAt(store.getCreatedAt())
                .build();
    }
}
