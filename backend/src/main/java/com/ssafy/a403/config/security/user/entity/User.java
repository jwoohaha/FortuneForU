package com.ssafy.a403.config.security.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(length = 30, nullable = false)
    private String nickname;

    @Embedded
    private Oauth2 oauth2;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> role = new ArrayList<>(List.of(Role.ROLE_USER));

    public User(String nickname, Oauth2 oauth2) {
        this.nickname = nickname;
        this.oauth2 = oauth2;
    }

    public List<SimpleGrantedAuthority> getAuthorityRole() {
        return role.stream()
                .map(Role::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
