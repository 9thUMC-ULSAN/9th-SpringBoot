package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.foodCategory.FoodCategory;
import com.example.umc9th.domain.foodCategory.FoodCategoryRepository;
import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.member.MemberPreference;
import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.dto.MemberResponseDto;
import com.example.umc9th.domain.member.repository.MemberPreferenceRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.Review;
import com.example.umc9th.domain.review.ReviewRepository;
import com.example.umc9th.domain.store.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MemberPreferenceRepository memberPreferenceRepository;

    @Override
    public MemberResponseDto.ReviewPreViewDto createReview(Long memberId, MemberRequestDto.ReviewDto request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        Review review = MemberConverter.toReview(request, member, store);
        reviewRepository.save(review);

        return MemberConverter.toReviewPreViewDto(review);
    }


    //회원가입
    @Override
    public MemberResponseDto.JoinDto signup(MemberRequestDto.JoinDto dto) {
        Member member = MemberConverter.toMember(dto);
        memberRepository.save(member);

        if (dto.getPreferCategory() != null && !dto.getPreferCategory().isEmpty()) {
            List<MemberPreference> memberPreferenceList = new ArrayList<>();

            for (Long id : dto.getPreferCategory()) {
                FoodCategory foodCategory = foodCategoryRepository.findById(id)
                        .orElseThrow(() -> new GeneralException(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));

                MemberPreference memberPreference = MemberPreference.builder()
                        .member(member)
                        .foodCategory(foodCategory)
                        .build();

                memberPreferenceList.add(memberPreference);
            }

            memberPreferenceRepository.saveAll(memberPreferenceList);
        }
        return MemberConverter.toJoinDto(member);
    }
}
