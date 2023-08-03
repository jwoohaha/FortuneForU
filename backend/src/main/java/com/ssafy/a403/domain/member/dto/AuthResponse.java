package com.ssafy.a403.domain.member.dto;

import com.ssafy.a403.global.config.security.jwt.JwtToken;
import lombok.Getter;

@Getter
public class AuthResponse {

    private final String accessToken;
    private final String refreshToken;

    public AuthResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static AuthResponse of(JwtToken jwtToken) {
        return new AuthResponse(jwtToken.getAccessToken(), jwtToken.getRefreshToken());
    }
}
