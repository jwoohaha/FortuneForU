package com.ssafy.a403.domain.entity;

import com.ssafy.a403.domain.enums.CounselorType;
import com.ssafy.a403.domain.enums.PassState;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Applications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationNo;

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

}
