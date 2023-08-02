package com.ssafy.a403.domain.reservation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateScheduleRequest {
    private Long counselorNo;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private String sun;
}
