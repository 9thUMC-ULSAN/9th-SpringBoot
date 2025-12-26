package com.example.umc9th.domain.auth.service;

import com.example.umc9th.domain.auth.dto.AuthRequestDto;
import com.example.umc9th.domain.auth.dto.AuthResponseDto;
import com.example.umc9th.domain.auth.exception.AuthException;
import com.example.umc9th.domain.auth.exception.code.AuthErrorCode;
import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.member.Role;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Profile("session")
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto.SignUpResponse signUp(AuthRequestDto.SignUpRequest request) {
        // 이메일 중복 체크
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new AuthException(AuthErrorCode.DUPLICATE_EMAIL);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Member 생성
        Member member = Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .phoneNum(request.getPhoneNum())
                .address(request.getAddress())
                .gender(request.getGender())
                .role(Role.ROLE_USER)
                .point(0)
                .build();

        Member savedMember = memberRepository.save(member);

        return AuthResponseDto.SignUpResponse.builder()
                .memberId(savedMember.getMemberId())
                .email(savedMember.getEmail())
                .name(savedMember.getName())
                .build();
    }

    @Override
    public AuthResponseDto.LoginResponse login(AuthRequestDto.LoginRequest request) {
        // 인증 수행
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // SecurityContext에 인증 정보 저장 (세션 생성)
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Member member = userDetails.getMember();

        return AuthResponseDto.LoginResponse.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .accessToken(null)  // Session 방식에서는 토큰 없음
                .build();
    }
}
