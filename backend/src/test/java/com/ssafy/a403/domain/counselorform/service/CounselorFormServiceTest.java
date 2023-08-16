//package com.ssafy.a403.domain.counselorform.service;
//
//
//import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
//import com.ssafy.a403.domain.counselorform.repository.CounselorFormRepository;
//import com.ssafy.a403.domain.member.entity.Member;
//import com.ssafy.a403.domain.member.repository.MemberRepository;
//import com.ssafy.a403.domain.model.CounselorType;
//import com.ssafy.a403.domain.model.PassState;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Slf4j
//@SpringBootTest
//public class CounselorFormServiceTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    CounselorFormRepository counselorFormRepository;
//    @Autowired
//    CounselorFormService counselorFormService;
//
//    @Test
//    @DisplayName("CounselorForm Details 조회")
//    public void counselorFormDetails() {
//
//    }
//
//    @Test
//    @DisplayName("CounselorForm 전체 조회")
//    @Transactional
//    public void counselorForms() {
//
//        // given
//        bulkInit();
//        PageRequest pageable = PageRequest.of(0, 6);
//
//        // when
//        Page<CounselorForm> counselorForms = counselorFormService.getAllCounselorForms("", pageable);
//
//        // then
//        assertThat(counselorForms.getTotalPages()).isEqualTo(3);
//        assertThat(counselorForms.getTotalElements()).isEqualTo(18);
//    }
//
//    @Test
//    @DisplayName("CounselorForm 필터링 조회 - PASS")
//    @Transactional
//    public void counselorFormsFilteringPass() {
//
//        // given
//        bulkInit();
//        PageRequest pageable = PageRequest.of(0, 6);
//
//        // when
//        Page<CounselorForm> counselorForms = counselorFormService.getAllCounselorForms("PASS", pageable);
//
//        // then
//        assertThat(counselorForms.getTotalPages()).isEqualTo(1);
//        assertThat(counselorForms.getTotalElements()).isEqualTo(6);
//        assertThat(counselorForms.getContent().get(0).getCounselorType()).isEqualTo(CounselorType.SAJU);
//    }
//
//    @Test
//    @DisplayName("CounselorForm 필터링 조회 - WAITING")
//    @Transactional
//    public void counselorFormsFilteringWating() {
//        // given
//        bulkInit();
//        PageRequest pageable = PageRequest.of(0, 6);
//
//        // when
//        Page<CounselorForm> counselorForms = counselorFormService.getAllCounselorForms("WAITING", pageable);
//
//        // then
//        assertThat(counselorForms.getTotalPages()).isEqualTo(1);
//        assertThat(counselorForms.getTotalElements()).isEqualTo(6);
//        assertThat(counselorForms.getContent().get(0).getCounselorType()).isEqualTo(CounselorType.TARO);
//    }
//
//    @Test
//    @DisplayName("CounselorForm 필터링 조회 - REJECT")
//    @Transactional
//    public void counselorFormsFilteringReject() {
//        // given
//        bulkInit();
//        PageRequest pageable = PageRequest.of(0, 6);
//
//        // when
//        Page<CounselorForm> counselorForms = counselorFormService.getAllCounselorForms("REJECT", pageable);
//
//        // then
//        assertThat(counselorForms.getTotalPages()).isEqualTo(1);
//        assertThat(counselorForms.getTotalElements()).isEqualTo(6);
//        assertThat(counselorForms.getContent().get(0).getCounselorType()).isEqualTo(CounselorType.BOTH);
//    }
//
//
//    /**
//     * 0, 3, 6: SAJU, PASS
//     * 1, 4, 7: TARO, WAITING
//     * 2, 5, 8: BOTH, REJECT
//     */
//    private void bulkInit() {
//        List<CounselorType> counselorTypes = List.of(CounselorType.SAJU, CounselorType.TARO, CounselorType.BOTH);
//        List<PassState> passStates = List.of(PassState.PASS, PassState.WAITING, PassState.REJECT);
//
//        List<Member> members = List.of(
//                Member.builder().email("abc123@gmail.com").name("김김김").profileImage("image1.png").build(),
//                Member.builder().email("abc456gmail.com").name("박박박").profileImage("image2.png").build(),
//                Member.builder().email("abc789@gmail.com").name("최최최").profileImage("image3.png").build()
//        );
//        memberRepository.saveAll(members);
//
//        List<CounselorForm> counselorForms = IntStream.range(0, 18).mapToObj(i ->
//                        CounselorForm.builder()
//                                .counselorType(counselorTypes.get(i % 3))
//                                .major("major" + i)
//                                .career("career" + i)
//                                .intro("intro" + i)
//                                .address("address" + i)
//                                .phone("phone" + i)
//                                .passState(passStates.get(i % 3))
//                                .member(members.get(i % 3))
//                                .build())
//                .collect(Collectors.toList());
//        counselorFormRepository.saveAll(counselorForms);
//    }
//}
