package com.ssafy.a403.domain.counselorform.service;

import com.ssafy.a403.domain.counselorform.dto.CounselorFormRequest;
import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
import com.ssafy.a403.domain.counselorform.repository.CounselorFormRepository;
import com.ssafy.a403.global.config.security.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CounselorFormService {

    private final CounselorFormRepository counselorFormRepository;

    @Transactional
    public void submitForm(CounselorFormRequest counselorFormRequest, LoginUser loginUser) {
        counselorFormRepository.save(counselorFormRequest.toCounselorForm(loginUser));
    }

    @Transactional
    public Page<CounselorForm> getAllCounselorForms(String filter, Pageable pageable) {

        return counselorFormRepository.findAllPaging(filter, pageable);
    }
}
