package com.ssafy.a403.domain.counselorform.entity;

import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.model.CounselorType;
import com.ssafy.a403.domain.model.PassState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class CounselorForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationNo;

    private int userNo;

    @Enumerated(EnumType.STRING)
    private CounselorType counselorType;

    private String major;

    private String intro;

    private String address;

    private String phone;

    @Enumerated(EnumType.STRING)
    private PassState passState;

    private LocalDateTime submitTime;

    private String reason;

    @Builder
    public CounselorForm(int userNo, CounselorType counselorType, String major, String intro, String address, String phone, PassState passState, LocalDateTime submitTime, String reason) {
        this.userNo = userNo;
        this.counselorType = counselorType;
        this.major = major;
        this.intro = intro;
        this.address = address;
        this.phone = phone;
        this.passState = passState;
        this.submitTime = submitTime;
        this.reason = reason;
    }
}
