package com.ssafy.a403.global.config.security.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtValidator jwtValidator;

    @Value("${jwt.access-header}")
    private String accessTokenHeaderTag;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> token = Optional.ofNullable(getTokensFromHeader(request));
        
        token.ifPresent(
                t -> {
                	log.info("AccessToken: {}", t);
                	Authentication authentication = jwtValidator.getAuthentication(t);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });
        filterChain.doFilter(request, response);
    }
    
    private String getTokensFromHeader(HttpServletRequest request) {
    	return request.getHeader(accessTokenHeaderTag);
    }

    private String getTokensFromCookies(Cookie[] cookies) {
        if (cookies == null) {
            return null;
        }

        Optional<Cookie> accessCookie = getAccessToken(cookies);
        return accessCookie.map(Cookie::getValue).orElse(null);
    }

    private Optional<Cookie> getAccessToken(Cookie[] cookies) {
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(accessTokenHeaderTag))
                .findFirst();
    }
}
