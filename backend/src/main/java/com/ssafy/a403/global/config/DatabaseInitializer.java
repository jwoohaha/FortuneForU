//package com.ssafy.a403.global.config;
//
//
//import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
//import com.ssafy.a403.domain.counselorform.repository.CounselorFormRepository;
//import com.ssafy.a403.domain.member.entity.Member;
//import com.ssafy.a403.domain.member.repository.MemberRepository;
//import com.ssafy.a403.domain.model.CounselorType;
//import com.ssafy.a403.domain.model.PassState;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.stream.IntStream;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class DatabaseInitializer {
//
//    private final MemberRepository memberRepository;
//    private final CounselorFormRepository counselorFormRepository;
//
//    @PostConstruct
//    public void init() {
//        log.info("Dummy Data Initializing");
//
//        initCounselorForms();
//    }
//
//    public void initCounselorForms() {
//        Member member = Member.builder().name("user1").build();
//        memberRepository.save(member);
//        IntStream.range(0, 6).mapToObj(i ->
//                        CounselorForm.builder()
//                                .counselorType(CounselorType.SAJU)
//                                .passState(PassState.PASS)
//                                .member(member)
//                                .build())
//                .forEach(counselorFormRepository::save);
//        IntStream.range(0, 6).mapToObj(i ->
//                        CounselorForm.builder()
//                                .counselorType(CounselorType.TARO)
//                                .passState(PassState.REJECT)
//                                .member(member)
//                                .build())
//                .forEach(counselorFormRepository::save);
//        IntStream.range(0, 6).mapToObj(i ->
//                        CounselorForm.builder()
//                                .counselorType(CounselorType.BOTH)
//                                .passState(PassState.WAITING)
//                                .member(member).
//                                build())
//                .forEach(counselorFormRepository::save);
//    }
//}
