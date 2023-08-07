package com.ssafy.a403.domain.member.entity;

import com.ssafy.a403.domain.model.CounselorType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class Counselor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counselor_no")
    private Long no;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="member_no")
    private Member member;

    @Enumerated(EnumType.STRING)
    private CounselorType counselorType;

    private String major;

    private String intro;

    private String address;

    private String phone;

    @Column(precision = 2, scale = 1)
    private BigDecimal counselorScore;

    @Builder
    public Counselor(Long no, Member member, CounselorType counselorType, String major, String intro,
                     String address, String phone, BigDecimal counselorScore) {
        this.no = no;
        this.member = member;
        this.counselorType = counselorType;
        this.major = major;
        this.intro = intro;
        this.address = address;
        this.phone = phone;
        this.counselorScore = counselorScore;
    }
}
