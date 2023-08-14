package com.ssafy.a403.domain.member.service;

import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.FollowRepository;
import com.ssafy.a403.domain.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// JUnit의 기본 LifeCycle은 PER_METHOD -> METHOD 단위로 테스트
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class FollowServiceTest {

    private static final Logger log = LoggerFactory.getLogger(FollowServiceTest.class);

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    FollowService followService;
    @Autowired
    FollowRepository followRepository;

    @BeforeAll
    void beforeAll() {
        List<String> names = List.of("박박박", "김김김", "최최최");
        log.trace("=========== Member Save ============");
        names.forEach((name) -> {
            memberRepository.save(Member.builder().name(name).build());
        });
    }

    @Test
    @DisplayName("상담사 팔로우 성공")
    @Transactional
    void followCounselorSuccess(){

        log.trace("=========== Member Find ============");
        // given
        Member follower = memberService.findById(1L);
        Member followee1 = memberService.findById(2L);
        Member followee2 = memberService.findById(3L);

        log.trace("=========== Follow Save ============");
        // when
        followService.follow(follower, followee1);
        followService.follow(follower, followee2);

        log.trace("=========== Follow Find ============");
        // then
        Assertions.assertThat(followService.followerList(follower).size()).isEqualTo(2);
        followService.followerList(follower).forEach(f -> log.trace("followee{} : {}", f.getNo(), f.getName()));
    }

    @Test
    @DisplayName("상담사 팔로우 중복")
    @Transactional
    void followDuplicatedCounselor(){

        log.trace("=========== Member Find ============");
        // given
        Member follower = memberService.findById(1L);
        Member followee1 = memberService.findById(2L);
        Member followee2 = memberService.findById(3L);

        log.trace("=========== Follow Save ============");
        // when
        followService.follow(follower, followee1);
        followService.follow(follower, followee2);
        followService.follow(follower, followee2);  // 중복 촣아요

        log.trace("=========== Follow Find ============");
        // then
        Assertions.assertThat(followService.followerList(follower).size()).isEqualTo(2);
        followService.followerList(follower).forEach(f -> log.trace("followee{} : {}", f.getNo(), f.getName()));
    }

    @Test
    @DisplayName("상담사 팔로우 취소")
    @Transactional
    void unfollowCounselor(){

        log.trace("=========== Member Find ============");
        // given
        Member follower = memberService.findById(1L);
        Member followee1 = memberService.findById(2L);
        Member followee2 = memberService.findById(3L);

        log.trace("=========== Follow Save ============");
        // when
        followService.follow(follower, followee1);
        followService.follow(follower, followee2);
        followService.unfollow(follower, followee1);    // followee1을 언팔로우

        log.trace("=========== Follow Find ============");
        // then
        Assertions.assertThat(followService.followerList(follower).size()).isEqualTo(1);
    }
}
