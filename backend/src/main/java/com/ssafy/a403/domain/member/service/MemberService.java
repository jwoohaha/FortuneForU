package com.ssafy.a403.domain.member.service;

import com.ssafy.a403.domain.member.dto.FollowerInfoResponse;
import com.ssafy.a403.domain.member.dto.MemberDetailsResponse;
import com.ssafy.a403.domain.member.dto.MemberInfoResponse;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

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
        List<FollowerInfoResponse> followerInfoList = followers.stream()
                .map(f -> FollowerInfoResponse.of(f))
                .collect(Collectors.toList());
        return MemberDetailsResponse.of(member, followerInfoList);
    }

    public Page<Member> findPaging(Pageable pageable) {
        return memberRepository.findPaging(pageable);
    }
}
