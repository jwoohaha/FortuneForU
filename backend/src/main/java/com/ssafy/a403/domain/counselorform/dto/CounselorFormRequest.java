package com.ssafy.a403.domain.counselorform.dto;

import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
import com.ssafy.a403.domain.model.CounselorType;
import com.ssafy.a403.domain.model.PassState;
import com.ssafy.a403.global.config.security.LoginUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CounselorFormRequest {

    private final CounselorType counselorType;

    private final String major;

    private final String career;

    private final String address;

    private final String phone;

    private final String intro;

    public CounselorForm toCounselorForm(LoginUser loginUser){

        return CounselorForm.builder()
                .counselorType(counselorType)
                .major(major)
                .career(career)
                .address(address)
                .phone(phone)
                .intro(intro)
                .member(loginUser.getMember())
                .passState(PassState.WAITING)
                .build();
    }
}
