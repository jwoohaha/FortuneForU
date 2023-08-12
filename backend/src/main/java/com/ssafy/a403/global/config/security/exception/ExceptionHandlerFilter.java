package com.ssafy.a403.global.config.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.a403.global.config.security.jwt.JwtValidator;;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter{

    @Value("${jwt.access-header}")
    private String accessTokenHeaderTag;

    @Value("${jwt.refresh-header}")
    private String refreshTokenHeaderTag;

    @Value("${client.url}")
    private String clientUrl;

    @Value("${client.endpoint}")
    private String tokenRedirectEndPoint;

    private final JwtValidator jwtValidator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e){
            log.debug("Access Token이 만료되었습니다.");
            validateRefreshToken(request, response);
        } catch (JwtException e) {
            log.debug("Access Token의 형식이 유효하지 않습니다.");
            validateRefreshToken(request, response);
            log.error("Filter Exception: {}", e.getMessage());
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, response, e);
        }
    }

    private void validateRefreshToken(HttpServletRequest request, HttpServletResponse response) {

        String refreshToken = getRefreshTokenFromCookies(request.getCookies());

        // 유효한 refresh token이라면
        if (refreshToken != null && isValidRefreshToken(refreshToken)){
            setRedirectTokenResponse(response, refreshToken);
            return; // return이 없다면 filterChain 계속 진행?
        }
        log.info("Refresh Token 재발급이 필요합니다.");
        setRedirectResponse(response, "/?login=true");
    }

    private boolean isValidRefreshToken(String token) {
        return jwtValidator.isValidToken(token);
    }

    // 토큰 만료 시, refresh token을 통해 access token 발급
    private void setRedirectTokenResponse(HttpServletResponse response, String authToken) {
        try {
            response.sendRedirect(clientUrl + tokenRedirectEndPoint + "?token=" + authToken);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void setRedirectResponse(HttpServletResponse response, String redirectEndPoint) {
        try {
            response.sendRedirect(clientUrl + redirectEndPoint);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable exception) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ErrorResponse errorResponse = ErrorResponse.of(status.value(), exception.getMessage());
        try {
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRefreshTokenFromCookies(Cookie[] cookies) {
        if (cookies == null) {
            return null;
        }
        Optional<Cookie> refreshCookie = getRefreshToken(cookies);
        return refreshCookie.map(Cookie::getValue).orElse(null);
    }

    private Optional<Cookie> getRefreshToken(Cookie[] cookies) {
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(refreshTokenHeaderTag))
                .findFirst();
    }
}
