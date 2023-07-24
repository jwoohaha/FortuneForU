package com.ssafy.a403.domain.entity;

import com.ssafy.a403.domain.enums.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNo;

    private String email;

    private String pwd;

    private String name;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String birth;

    private boolean isActive = true;

    @Builder
    public Users(String email, String pwd, String name, String nickname, UserType userType, String birth) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.nickname = nickname;
        this.userType = userType;
        this.birth = birth;
    }
}
