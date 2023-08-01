package com.ssafy.a403.domain.reservation.controller;

import com.ssafy.a403.domain.reservation.entity.CounselingSchedule;
import com.ssafy.a403.domain.reservation.service.CounselingScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CounselingScheduleController {

    private final CounselingScheduleService counselingScheduleService;

    // Todo: 권한 추가
    @GetMapping("/counselor/time/{memberNo}")
    public CounselingSchedule getCounselingSchedule(@PathVariable("memberNo") Long id) {
        return counselingScheduleService.findOne(id);
    }
}
