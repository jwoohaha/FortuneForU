package com.ssafy.a403.config.security;

import java.util.Collection;
import java.util.Map;

import com.ssafy.a403.config.security.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;

@Getter
public class LoginUser implements UserDetails, OAuth2User {

	private final User user;
    private final Map<String, Object> attribute;
    private final Collection<? extends GrantedAuthority> authorities;

    public LoginUser(User user, Map<String, Object> attribute, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.attribute = attribute;
        this.authorities = authorities;
    }

    @Override
    public String getName() {
        return user.getId().toString();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attribute;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "password";
    }

    @Override
    public String getUsername() {
        return user.getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
