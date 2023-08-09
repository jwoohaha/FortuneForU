package com.ssafy.a403.domain.member.service;

import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemberServiceTest {

    private static final Logger log = LoggerFactory.getLogger(MemberServiceTest.class);

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        List<String> names = List.of("김김김", "최최최", "박박박");
        names.forEach(name -> memberRepository.save(Member.builder().name(name).build()));
    }

    @AfterEach
    void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 조회 성공")
    void memberFind() {
        Member member = memberService.findById(1L);
        assertThat(member.getNo()).isEqualTo(1L);
    }

    @Test
    @DisplayName("회원 조회 실패")
    void memberFindError() {
        assertThatThrownBy(() -> memberService.findById(100L)).isInstanceOf(EntityNotFoundException.class);
    }
}
