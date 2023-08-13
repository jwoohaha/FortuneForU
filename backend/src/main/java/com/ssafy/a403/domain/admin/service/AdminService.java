package com.ssafy.a403.domain.admin.service;

import com.ssafy.a403.domain.counselorform.dto.CounselorFormDetailsResponse;
import com.ssafy.a403.domain.counselorform.dto.CounselorFormResponse;
import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
import com.ssafy.a403.domain.counselorform.service.CounselorFormService;
import com.ssafy.a403.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AdminService{

    private final CounselorFormService counselorFormService;
    private final MemberService memberService;

    public List<CounselorFormResponse> counselorFormList(String filter, Pageable pageable){

        Page<CounselorForm> forms = counselorFormService.getAllCounselorForms(filter, pageable);
        return forms.stream()
                .map(CounselorFormResponse::from)
                .collect(Collectors.toList());
    }

    public CounselorFormDetailsResponse counselorFormDetail(Long counselorFormNo) {

        CounselorForm counselorForm = counselorFormService.getCounselorForm(counselorFormNo);
        return CounselorFormDetailsResponse.of(counselorForm);
    }
}
