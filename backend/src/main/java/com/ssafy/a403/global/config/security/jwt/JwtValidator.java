package com.ssafy.a403.global.config.security.jwt;

import java.security.Key;

import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.global.config.security.LoginUser;
import com.ssafy.a403.global.config.security.oauth.mapper.LoginUserMapper;
import com.ssafy.a403.domain.member.service.MemberService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtValidator {
	private final Key key;
    private final MemberService memberService;
    private final LoginUserMapper loginUserMapper;

    public Authentication getAuthentication(String accessToken) throws NumberFormatException, EntityNotFoundException {
        Claims claims = getTokenClaims(accessToken);
        // TODO: 토큰 만료 확인 로직 필요
        Member member = memberService.findById(Long.parseLong(claims.get("id", String.class)));
        LoginUser loginUser = loginUserMapper.toLoginUser(member);

        return new UsernamePasswordAuthenticationToken(loginUser, "", loginUser.getAuthorities());
    }
    
    public LoginUser getLoginUser(String token) throws NumberFormatException, EntityNotFoundException {
    	Claims claims = getTokenClaims(token);
        Member member = memberService.findById(Long.parseLong(claims.get("id", String.class)));
        return loginUserMapper.toLoginUser(member);
    }

    private Claims getTokenClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
