package com.ssafy.a403.admin.response;

import com.ssafy.a403.domain.entity.User;
import com.ssafy.a403.domain.enums.CounselorType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FormResponse {

    Long applicationNo;

    User user;

    LocalDateTime submitTime;

    CounselorType counselorType;

    @Builder
    public FormResponse(Long applicationNo, User user, LocalDateTime submitTime, CounselorType counselorType) {
        this.applicationNo = applicationNo;
        this.user = user;
        this.submitTime = submitTime;
        this.counselorType = counselorType;
    }
}
