package com.ssafy.a403.domain.member.service;

import com.ssafy.a403.domain.member.entity.Counselor;
import com.ssafy.a403.domain.member.repository.CounselorRepository;
import com.ssafy.a403.domain.reservation.entity.CounselingSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CounselorService {
    private final CounselorRepository counselorRepository;

    // 상담가 조회
    public Counselor findById(Long id) {
        return counselorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    // 상담가 전체 조회
    public List<Counselor> findCounselors() { return counselorRepository.findAll(); }
}
