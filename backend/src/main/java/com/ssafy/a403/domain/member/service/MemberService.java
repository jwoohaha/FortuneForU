package com.ssafy.a403.domain.member.service;

import com.ssafy.a403.domain.counselorform.dto.CounselorFormRequest;
import com.ssafy.a403.domain.counselorform.service.CounselorFormService;
import com.ssafy.a403.domain.member.dto.MemberDetailsResponse;
import com.ssafy.a403.domain.member.dto.MemberInfoResponse;
import com.ssafy.a403.domain.member.entity.Follow;
import com.ssafy.a403.domain.member.entity.FollowId;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.MemberRepository;
import com.ssafy.a403.global.config.security.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final CounselorFormService counselorFormService;
    private final FollowService followService;
    private final MemberRepository memberRepository;

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public MemberInfoResponse getMemberInfo(Member member) {
        return MemberInfoResponse.of(member);
    }

    public MemberDetailsResponse getMemberDetails(Member member){

        List<Member> followers = followService.followerList(member);
        return MemberDetailsResponse.of(member, followers);
    }

    @Transactional
    public void follow(Member follower, Member followee) {

        followService.follow(follower, followee);
    }

    @Transactional
    public void unfollow(Member follower, Member followee) {

        followService.unfollow(follower, followee);
    }

    @Transactional
    public void submitCounselorForm(CounselorFormRequest counselorFormRequest, LoginUser loginUser) {

        counselorFormService.submitForm(counselorFormRequest, loginUser);
    }

    public Page<Member> findPaging(Pageable pageable) {
        return memberRepository.findPaging(pageable);
    }
}
