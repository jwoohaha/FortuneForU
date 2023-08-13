package com.ssafy.a403.domain.admin.service;

import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
import com.ssafy.a403.domain.counselorform.service.CounselorFormService;
import com.ssafy.a403.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AdminService{

    private final CounselorFormService counselorFormService;
    private final MemberService memberService;

    @Transactional
    public Page<CounselorForm> counselorFormList(String filter, Pageable pageable){

        return counselorFormService.getAllCounselorForms(filter, pageable);
    }
}
