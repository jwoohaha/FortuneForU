package com.ssafy.a403.domain.member.dto;

import com.ssafy.a403.domain.model.PassState;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CounselorFormRequest {

    private final Long no;

    private final String name;

    private final LocalDateTime created;

    private final PassState status;

    public CounselorFormRequest(Long no, String name, LocalDateTime created, PassState status) {
        this.no = no;
        this.name = name;
        this.created = created;
        this.status = status;
    }

    public static CounselorFormRequest of(Long no, String name, LocalDateTime created, PassState status){
        return new CounselorFormRequest(no, name, created, status);
    }
}
