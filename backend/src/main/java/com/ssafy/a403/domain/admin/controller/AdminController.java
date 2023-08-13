package com.ssafy.a403.domain.admin.controller;

import com.ssafy.a403.domain.admin.service.AdminService;
import com.ssafy.a403.domain.counselorform.dto.CounselorFormResponse;
import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/api/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/counselor-forms")
    public ResponseEntity<List<CounselorFormResponse>> counselorForms(@RequestParam String filter, Pageable pageable){
        log.trace(filter);
        Page<CounselorForm> forms = adminService.counselorFormList(filter, pageable);
        return new ResponseEntity<>(forms.stream()
                .map(CounselorFormResponse::from)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
