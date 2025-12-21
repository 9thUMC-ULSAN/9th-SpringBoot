package com.example.umc9th.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class StoreResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateStoreResultDto {
        private Long storeId;
        private String name;
        private String address;
        private String regionName;
        private LocalDateTime createdAt;
    }
}
