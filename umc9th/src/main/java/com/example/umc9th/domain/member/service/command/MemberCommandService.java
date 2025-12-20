package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.MemberResDTO;

public interface MemberCommandService {
    //회원가입
    MemberResDTO.JoinDTO signup(
            MemberResDTO.JoinDTO dto
    );
}
