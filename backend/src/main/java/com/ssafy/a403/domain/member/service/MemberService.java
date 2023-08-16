package com.ssafy.a403.domain.member.service;

import com.ssafy.a403.domain.member.dto.FollowerInfoResponse;
import com.ssafy.a403.domain.member.dto.MemberDetailsResponse;
import com.ssafy.a403.domain.member.dto.MemberInfoResponse;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    @Transactional
    public void updateProfileImage(Long id, MultipartFile profileImageFile) throws IOException {
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        // 경로 설정
        String ImgName = System.currentTimeMillis() + "_" + profileImageFile.getOriginalFilename(); // 이름 중복 방지
        String Path = "/opt/images/" + ImgName;

        // 저장
        File f = new File(Path);
        profileImageFile.transferTo(f);

        // 기존 파일 삭제
        try{
            File originalFile = new File(member.getProfileImage());
            if (originalFile.exists()) {
                originalFile.delete();
            }
        } catch(Exception e){
            log.error("기존 파일이 존재하지 않습니다");
        }
        member.updateProfileImage(Path);
    }
}
