package com.ssafy.a403.domain.member.dto;

import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.model.Role;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberDetailsResponse extends MemberInfoResponse {

    private final List<Member> followers;

    public MemberDetailsResponse(String email, String name, String profileImage, List<Role> roles, List<Member> followers) {
        super(email, name, profileImage, roles);
        this.followers = followers;
    }

    public static MemberDetailsResponse of(Member member, List<Member> followers) {
        return new MemberDetailsResponse(member.getEmail(), member.getName(), member.getProfileImage(), member.getRoles(), followers);
    }
}
