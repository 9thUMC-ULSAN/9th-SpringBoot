package com.example.umc9th.domain.store.service.command;

import com.example.umc9th.domain.region.Region;
import com.example.umc9th.domain.region.repository.RegionRepository;
import com.example.umc9th.domain.store.Store;
import com.example.umc9th.domain.store.converter.StoreConverter;
import com.example.umc9th.domain.store.dto.StoreRequestDto;
import com.example.umc9th.domain.store.dto.StoreResponseDto;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    public StoreResponseDto.CreateStoreResultDto createStore(Integer regionId, StoreRequestDto.CreateStoreDto request) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.REGION_NOT_FOUND));

        Store store = StoreConverter.toStore(request, region);
        storeRepository.save(store);

        return StoreConverter.toCreateStoreResultDto(store);
    }
}
