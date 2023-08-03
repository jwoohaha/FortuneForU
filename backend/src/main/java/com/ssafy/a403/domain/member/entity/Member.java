package com.ssafy.a403.domain.member.entity;

import com.ssafy.a403.domain.model.Oauth2;
import com.ssafy.a403.domain.model.Role;
import com.ssafy.a403.global.config.security.oauth.mapper.AuthProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long no;

    private String email;

    @Column(unique = true)
    private String name;

    @ColumnDefault("true")
    private Boolean isActive;

    private String profileImage;

    @OneToOne(mappedBy = "member")
    private Counselor counselor;

    @Embedded
    private Oauth2 oauth2;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="member_role", joinColumns = @JoinColumn(name= "member_no", referencedColumnName = "member_no"))
    private List<Role> role = new ArrayList<>(List.of(Role.ROLE_USER));

    @Builder
    public Member(Long no, String email, String name, String profileImage,
                  String accountId, AuthProvider authProvider) {
        this.no = no;
        this.email = email;
        this.name = name;
        this.profileImage = profileImage;
        this.oauth2 = new Oauth2(authProvider, accountId);
    }

    public List<SimpleGrantedAuthority> getAuthorityRole() {
        return role.stream()
                .map(Role::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    // 테스트용!!!!!!!!!!!!!!!!!!!
    public void setId(Long memberId) {
        this.no = memberId;
    }
}
