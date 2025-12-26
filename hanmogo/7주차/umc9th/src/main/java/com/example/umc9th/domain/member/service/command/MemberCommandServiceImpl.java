package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.member.MemberRepository;
import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberRequestDto;
import com.example.umc9th.domain.member.dto.MemberResponseDto;
import com.example.umc9th.domain.review.Review;
import com.example.umc9th.domain.review.ReviewRepository;
import com.example.umc9th.domain.store.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public MemberResponseDto.ReviewPreViewDto createReview(Long memberId, MemberRequestDto.ReviewDto request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(request.getId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        Review review = MemberConverter.toReview(request);
        review.setMember(member);
        review.setStore(store);

        reviewRepository.save(review);

        return MemberConverter.toReviewPreViewDto(review);
    }
}