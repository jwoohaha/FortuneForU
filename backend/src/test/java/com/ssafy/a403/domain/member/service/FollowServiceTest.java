//package com.ssafy.a403.domain.member.service;
//
//import com.ssafy.a403.domain.member.entity.Member;
//import com.ssafy.a403.domain.member.repository.FollowRepository;
//import com.ssafy.a403.domain.member.repository.MemberRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//// JUnit의 기본 LifeCycle은 PER_METHOD -> METHOD 단위로 테스트
//@SpringBootTest
//@Slf4j
//public class FollowServiceTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    FollowService followService;
//    @Autowired
//    FollowRepository followRepository;
//
//    @Test
//    @DisplayName("상담사 팔로우 성공")
//    @Transactional
//    void followCounselorSuccess(){
//        List<Member> members = init();
//
//        log.trace("=========== Member Find ============");
//        // given
//        Member follower = memberService.findById(members.get(0).getNo());
//        Member followee1 = memberService.findById(members.get(1).getNo());
//        Member followee2 = memberService.findById(members.get(2).getNo());
//
//        log.trace("=========== Follow Save ============");
//        // when
//        followService.follow(follower, followee1);
//        followService.follow(follower, followee2);
//
//        log.trace("=========== Follow Find ============");
//        // then
//        Assertions.assertThat(followService.followerList(follower).size()).isEqualTo(2);
//        followService.followerList(follower).forEach(f -> log.trace("followee{} : {}", f.getNo(), f.getName()));
//    }
//
//    @Test
//    @DisplayName("상담사 팔로우 중복")
//    @Transactional
//    void followDuplicatedCounselor(){
//        List<Member> members = init();
//
//        log.trace("=========== Member Find ============");
//        // given
//        Member follower = memberService.findById(members.get(0).getNo());
//        Member followee1 = memberService.findById(members.get(1).getNo());
//        Member followee2 = memberService.findById(members.get(2).getNo());
//
//        log.trace("=========== Follow Save ============");
//        // when
//        followService.follow(follower, followee1);
//        followService.follow(follower, followee2);
//        followService.follow(follower, followee2);  // 중복 촣아요
//
//        log.trace("=========== Follow Find ============");
//        // then
//        Assertions.assertThat(followService.followerList(follower).size()).isEqualTo(2);
//        followService.followerList(follower).forEach(f -> log.trace("followee{} : {}", f.getNo(), f.getName()));
//    }
//
//    @Test
//    @DisplayName("상담사 팔로우 취소")
//    @Transactional
//    void unfollowCounselor(){
//        List<Member> members = init();
//
//        log.trace("=========== Member Find ============");
//        // given
//        Member follower = memberService.findById(members.get(0).getNo());
//        Member followee1 = memberService.findById(members.get(1).getNo());
//        Member followee2 = memberService.findById(members.get(2).getNo());
//
//        log.trace("=========== Follow Save ============");
//        // when
//        followService.follow(follower, followee1);
//        followService.follow(follower, followee2);
//        followService.unfollow(follower, followee1);    // followee1을 언팔로우
//
//        log.trace("=========== Follow Find ============");
//        // then
//        Assertions.assertThat(followService.followerList(follower).size()).isEqualTo(1);
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
