package com.ssafy.a403.domain.admin.service;

import com.ssafy.a403.domain.counselorform.dto.CounselorFormDetailsResponse;
import com.ssafy.a403.domain.counselorform.dto.CounselorFormResponse;
import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
import com.ssafy.a403.domain.counselorform.service.CounselorFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final CounselorFormService counselorFormService;

    public Page<CounselorFormResponse> counselorFormList(String filter, Pageable pageable) {

        Page<CounselorFormResponse> counselorFormResponses =
                counselorFormService.getAllCounselorForms(filter, pageable)
                        .map(CounselorFormResponse::from);
        return counselorFormResponses;
    }

    public CounselorFormDetailsResponse counselorFormDetail(Long counselorFormNo) {

        CounselorForm counselorForm = counselorFormService.getCounselorForm(counselorFormNo);
        return CounselorFormDetailsResponse.of(counselorForm);
    }
}
