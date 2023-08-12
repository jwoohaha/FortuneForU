package com.ssafy.a403.domain.member.controller;

import com.ssafy.a403.domain.member.dto.AuthRequest;
import com.ssafy.a403.domain.member.dto.AuthResponse;
import com.ssafy.a403.global.config.security.jwt.JwtSetupService;
import com.ssafy.a403.global.config.security.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final JwtSetupService jwtSetupService;

    @PostMapping
    public ResponseEntity<AuthResponse> authToken(@RequestBody @Validated AuthRequest authRequest) {
        log.trace("Auth Token: {}", authRequest.getAuthToken());

        AuthResponse authResponse = jwtSetupService.createJwtTokenByAuthToken(authRequest.getAuthToken());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, authResponse.getAccessToken());
        headers.add(HttpHeaders.SET_COOKIE, authResponse.getRefreshToken());
        log.trace(authResponse.getRefreshToken());
        return ResponseEntity.ok().headers(headers).build();
    }

    @GetMapping("/test")
    public ResponseEntity<?> testMethod(HttpServletRequest request) {
        log.trace("====== TEST ======");
        log.trace("access-token: {}", request.getHeader("Authorization"));
        Optional<Cookie> refreshCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("Refresh"))
                .findFirst();
        refreshCookie.ifPresent(cookie -> {
            log.trace("refresh-token: {}", refreshCookie.map(Cookie::getValue));
        });

        return ResponseEntity.ok().build();
    }
}
