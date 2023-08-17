//package com.ssafy.a403.domain.member.service;
//
//import com.ssafy.a403.domain.member.entity.Member;
//import com.ssafy.a403.domain.member.repository.MemberRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//@Slf4j
//public class MemberServiceTest {
//
//    @Autowired
//    private MemberService memberService;
//    @Autowired
//    private MemberRepository memberRepository;
//
//
//    @Test
//    @DisplayName("회원 ID 조회 성공")
//    @Transactional
//    void memberFindById() {
//        List<Member> members = init();
//
//        Member member = memberService.findById(members.get(0).getNo());
//        log.trace("memberNo: {}", members.get(0).getNo());
//
//        assertThat(member.getEmail()).isEqualTo("abc123@gmail.com");
//        assertThat(member.getName()).isEqualTo("김김김");
//        assertThat(member.getProfileImage()).isEqualTo("image1.png");
//    }
//
//    @Test
//    @DisplayName("회원 ID 조회 실패")
//    @Transactional
//    void memberFindByIdFail() {
//
//        assertThatThrownBy(() -> memberService.findById(100L)).isInstanceOf(EntityNotFoundException.class);
//    }
//
//    private List<Member> init() {
//        List<Member> members = List.of(
//                Member.builder().email("abc123@gmail.com").name("김김김").profileImage("image1.png").build(),
//                Member.builder().email("abc456gmail.com").name("박박박").profileImage("image2.png").build(),
//                Member.builder().email("abc789@gmail.com").name("최최최").profileImage("image3.png").build()
//        );
//        memberRepository.saveAll(members);
//
//        return members;
//    }
//}
