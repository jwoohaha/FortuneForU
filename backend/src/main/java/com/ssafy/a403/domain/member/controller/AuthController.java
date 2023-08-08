package com.ssafy.a403.domain.member.controller;

import com.ssafy.a403.domain.member.dto.AuthRequest;
import com.ssafy.a403.domain.member.dto.AuthResponse;
import com.ssafy.a403.global.config.security.jwt.JwtSetupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final JwtSetupService jwtSetupService;

    @PostMapping
    public ResponseEntity<AuthResponse> authToken(@RequestBody @Validated AuthRequest authRequest) {
        log.trace("Auth Token: {}", authRequest.getAuthToken());

        return ResponseEntity.ok(
                AuthResponse.of(
                        jwtSetupService.createJwtTokenByAuthToken(authRequest.getAuthToken())));
    }
}
