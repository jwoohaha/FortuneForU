package com.ssafy.a403.domain.reservation.service;

import com.ssafy.a403.domain.reservation.entity.CounselingSchedule;
import com.ssafy.a403.domain.reservation.repository.CounselingScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CounselingScheduleService {

    private final CounselingScheduleRepository counselingScheduleRepository;

    // 상담 가능 시간 조회
    public CounselingSchedule findOne(Long id) { return counselingScheduleRepository.getReferenceById(id); }

}

