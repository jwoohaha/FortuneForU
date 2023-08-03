package com.ssafy.a403.domain.reservation.dto;

import com.ssafy.a403.domain.reservation.entity.CounselingSchedule;
import lombok.Data;

@Data
public class ScheduleResponse {
    private Long counselorNo;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private String sun;

    public static ScheduleResponse from(CounselingSchedule counselingSchedule) {
        ScheduleResponse scheduleResponse = new ScheduleResponse();

        scheduleResponse.counselorNo = counselingSchedule.getCounselor().getNo();
        scheduleResponse.mon = counselingSchedule.getMon();
        scheduleResponse.tue = counselingSchedule.getTue();
        scheduleResponse.wed = counselingSchedule.getWed();
        scheduleResponse.thu = counselingSchedule.getThu();
        scheduleResponse.fri = counselingSchedule.getFri();
        scheduleResponse.sat = counselingSchedule.getSat();
        scheduleResponse.sun = counselingSchedule.getSun();

        return scheduleResponse;
    }
}
