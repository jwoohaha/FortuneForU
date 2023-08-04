package com.ssafy.a403.domain.member.dto;

import com.ssafy.a403.domain.member.entity.Counselor;
import lombok.Data;

@Data
public class CounselorInfoResponse {
    private Long counselorNo;
    private Enum CounselorType;
    private String major;
    private String intro;
    private String address;
    private String phone;

    public static CounselorInfoResponse from(Counselor counselor) {
        CounselorInfoResponse counselorInfoResponse = new CounselorInfoResponse();

        counselorInfoResponse.counselorNo = counselor.getNo();
        counselorInfoResponse.CounselorType = counselor.getCounselorType();
        counselorInfoResponse.major = counselor.getMajor();
        counselorInfoResponse.intro = counselor.getIntro();
        counselorInfoResponse.address = counselor.getAddress();
        counselorInfoResponse.phone = counselor.getPhone();

        return counselorInfoResponse;
    }
}
