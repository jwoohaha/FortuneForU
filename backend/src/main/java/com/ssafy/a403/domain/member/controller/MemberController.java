package com.ssafy.a403.domain.member.controller;

import com.ssafy.a403.domain.member.dto.MemberDetailsResponse;
import com.ssafy.a403.domain.member.dto.MemberInfoResponse;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.service.FollowService;
import com.ssafy.a403.domain.member.service.MemberService;
import com.ssafy.a403.global.config.security.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final FollowService followService;

    @GetMapping("/test")
    public ResponseEntity testMethod(HttpServletRequest request) {
        log.trace("====== TEST ======");
        log.trace("access-token: {}", request.getHeader("Authorization"));
        log.trace("refresh-token: {}", request.getHeader("Refresh"));

        return new ResponseEntity(HttpStatus.OK);
    }

    // Member 정보 조회
    @GetMapping("/info")
    public ResponseEntity<MemberInfoResponse> memberInfo(@AuthenticationPrincipal LoginUser loginUser) {
        log.trace("loginUser: {}", loginUser.getName());

        return new ResponseEntity<>(
                memberService.getMemberInfo(loginUser.getMember()),
                HttpStatus.OK
        );
    }

    // MyPage에서 보여줄 Member 정보 조회 (followers 추가)
    @GetMapping("/details")
    public ResponseEntity<MemberDetailsResponse> memberDetails(@AuthenticationPrincipal LoginUser loginUser) {

        return new ResponseEntity<>(
                memberService.getMemberDetails(loginUser.getMember()),
                HttpStatus.OK
        );
    }

    @GetMapping("/logout")
    public void logout() {

        // TODO: 로그아웃 -> refresh token 삭제
    }

    @PutMapping("follow/{followeeId}")
    public HttpStatus follow(@PathVariable Long followeeId, @AuthenticationPrincipal LoginUser loginUser) {

        followService.follow(loginUser.getMember(), memberService.findById(followeeId));
        return HttpStatus.OK;
    }

    @DeleteMapping("unfollow/{followeeId}")
    public HttpStatus unfollow(@PathVariable Long followeeId, @AuthenticationPrincipal LoginUser loginUser) {

        followService.unfollow(loginUser.getMember(), memberService.findById(followeeId));
        return HttpStatus.OK;
    }

    @PutMapping(value = "/profileImage", consumes = {"multipart/form-data"})
    public HttpStatus updateProfileImage(@AuthenticationPrincipal LoginUser loginUser,
                                         @RequestPart("image") MultipartFile profileImageFile) throws Exception {
        String HomeDirectory = System.getProperty("user.home");
        String ImgName = System.currentTimeMillis() + "_" + profileImageFile.getOriginalFilename(); // 이름 중복 방지
        String Path = HomeDirectory + "/" + ImgName;
        File f = new File(Path);
        profileImageFile.transferTo(f);
        log.info("저장된 파일 정보={}", f);
        memberService.updateProfileImage(loginUser.getMember().getNo(), Path);
        return HttpStatus.OK;
    }
}
