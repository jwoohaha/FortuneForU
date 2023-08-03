package com.ssafy.a403.domain.member.controller;

import com.ssafy.a403.domain.member.dto.CounselorInfoResponse;
import com.ssafy.a403.domain.member.entity.Counselor;
import com.ssafy.a403.domain.member.service.CounselorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CounselorController {

    private final CounselorService counselorService;

    /**
     * 상담사 정보 조회
     */
    @GetMapping("/counselor/{counselorNo}")
    public ResponseEntity<CounselorInfoResponse> getCounselorInfo(@PathVariable("counselorNo") Long counselorNo) {
        Counselor counselor = counselorService.findById(counselorNo);
        return ResponseEntity.ok(CounselorInfoResponse.from(counselor));
    }

    /**
     * 상담사 전체 조회
     */
    @GetMapping("/counselors")
    public ResponseEntity<List<CounselorInfoResponse>> getCounselorInfoList() {
        List<Counselor> findCounselors = counselorService.findCounselors();
        List<CounselorInfoResponse> counselorInfoList = findCounselors.stream()
                .map(m -> CounselorInfoResponse.from(m))
                .collect(Collectors.toList());
        return ResponseEntity.ok(counselorInfoList);
    }

}
