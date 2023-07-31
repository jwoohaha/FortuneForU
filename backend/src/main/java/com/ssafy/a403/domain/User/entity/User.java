package com.ssafy.a403.domain.User.entity;

import com.ssafy.a403.domain.model.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {

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

    private String profileImage;

    @OneToOne
    @JoinColumn(name = "counselorNo")
    private Counselor counselorInfo;

    @Builder
    public User(String email, String pwd, String name, String nickname, UserType userType, String birth) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.nickname = nickname;
        this.userType = userType;
        this.birth = birth;
    }
}
