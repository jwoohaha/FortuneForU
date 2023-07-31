package com.ssafy.a403.domain.admin.service;

import com.ssafy.a403.domain.member.dto.FormResponse;
import com.ssafy.a403.domain.member.entity.CounselorForm;
import com.ssafy.a403.domain.member.repository.CounselorFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final CounselorFormRepository counselorFormRepository;

    @Override
    public List<FormResponse> getAllForms(){

        List<CounselorForm> forms = counselorFormRepository.findAll();
        List<FormResponse> formResponseList = new ArrayList<>();

        for(CounselorForm form : forms){
            FormResponse response = FormResponse.builder()
                    .applicationNo(form.getApplicationNo())
                    .submitTime(form.getSubmitTime())
                    .counselorType(form.getCounselorType())
                    .build();

            formResponseList.add(response);
        }

        return formResponseList;
    }
}
