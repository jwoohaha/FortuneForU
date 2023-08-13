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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class MemberServiceTest {

    private static final Logger log = LoggerFactory.getLogger(MemberServiceTest.class);

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeAll
    void beforeAll() {

        List<Member> members = List.of(
                Member.builder().email("abc123@gmail.com").name("김김김").profileImage("image1.png").build(),
                Member.builder().email("abc456gmail.com").name("박박박").profileImage("image2.png").build(),
                Member.builder().email("abc789@gmail.com").name("최최최").profileImage("image3.png").build()
        );
        memberRepository.saveAll(members);
    }

    @Test
    @DisplayName("회원 ID 조회 성공")
    void memberFindById() {
        Member member = memberService.findById(1L);

        assertThat(member.getNo()).isEqualTo(1L);
        assertThat(member.getEmail()).isEqualTo("abc123@gmail.com");
        assertThat(member.getName()).isEqualTo("김김김");
        assertThat(member.getProfileImage()).isEqualTo("image1.png");
    }

    @Test
    @DisplayName("회원 ID 조회 실패")
    void memberFindByIdFail() {
        assertThatThrownBy(() -> memberService.findById(4L)).isInstanceOf(EntityNotFoundException.class);
    }
}
