package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.MemberPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPreferenceRepository extends JpaRepository<MemberPreference, Long> {
}
