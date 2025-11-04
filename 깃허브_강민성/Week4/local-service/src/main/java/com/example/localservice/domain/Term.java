package com.example.localservice.domain;
import jakarta.persistence.*;
import lombok.*;
import com.example.localservice.type.TermType;

@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true) // DB DDL에 따라 NULL 허용
    private TermType name;

    @Lob @Column(nullable = false)
    private String contents;
}