package com.ssafy.a403.domain.user.entity;

import com.ssafy.a403.domain.model.CounselorType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Counselor {

    @Id
    private Long counselorNo;

    @Enumerated(EnumType.STRING)
    private CounselorType counselorType;

    private String major;

    private String intro;

    private String address;

    private String phone;

    @Builder
    public Counselor(CounselorType counselorType, String major, String intro,
                     String address, String phone) {
        this.counselorType = counselorType;
        this.major = major;
        this.intro = intro;
        this.address = address;
        this.phone = phone;
    }


}
