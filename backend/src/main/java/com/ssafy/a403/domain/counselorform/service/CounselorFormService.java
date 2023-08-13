package com.ssafy.a403.domain.counselorform.service;

import com.ssafy.a403.domain.counselorform.dto.CounselorFormRequest;
import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
import com.ssafy.a403.domain.counselorform.repository.CounselorFormRepository;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.global.config.security.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CounselorFormService {

    private final CounselorFormRepository counselorFormRepository;

    @Transactional(readOnly = true)
    public Page<CounselorForm> getAllCounselorForms(String filter, Pageable pageable) {

        return counselorFormRepository.findAllPaging(filter, pageable);
    }

    @Transactional(readOnly = true)
    public CounselorForm getCounselorForm(Long counselorFormNo) {

        return counselorFormRepository.findById(counselorFormNo).orElseThrow(EntityNotFoundException::new);
    }
}
