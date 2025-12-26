package com.example.umc9th.domain.auth.service;

import com.example.umc9th.domain.auth.dto.AuthRequestDto;
import com.example.umc9th.domain.auth.dto.AuthResponseDto;
import com.example.umc9th.domain.auth.exception.AuthException;
import com.example.umc9th.domain.auth.exception.code.AuthErrorCode;
import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.member.Role;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Profile("jwt")
public class JwtAuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

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
        // 이메일로 회원 조회
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException(AuthErrorCode.INVALID_CREDENTIALS));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new AuthException(AuthErrorCode.INVALID_CREDENTIALS);
        }

        // JWT 토큰 생성
        String accessToken = jwtTokenProvider.createAccessToken(member.getEmail(), member.getRole().name());

        return AuthResponseDto.LoginResponse.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .accessToken(accessToken)
                .build();
    }
}
