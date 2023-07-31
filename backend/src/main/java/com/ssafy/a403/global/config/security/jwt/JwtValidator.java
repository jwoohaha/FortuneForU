package com.ssafy.a403.global.config.security.jwt;

import java.security.Key;

import com.ssafy.a403.global.config.security.LoginUser;
import com.ssafy.a403.global.config.security.oauth.mapper.LoginUserMapper;
import com.ssafy.a403.global.config.security.user.entity.User;
import com.ssafy.a403.global.config.security.user.service.UserService;
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
    private final UserService userService;
    private final LoginUserMapper loginUserMapper;

    public Authentication getAuthentication(String accessToken) throws NumberFormatException, EntityNotFoundException {
        Claims claims = getTokenClaims(accessToken);
        User user = userService.findById(Long.parseLong(claims.get("id", String.class)));
        LoginUser loginUser = loginUserMapper.toLoginUser(user);

        return new UsernamePasswordAuthenticationToken(loginUser, "", loginUser.getAuthorities());
    }
    
    public LoginUser getLoginUser(String token) throws NumberFormatException, EntityNotFoundException {
    	Claims claims = getTokenClaims(token);
        User user = userService.findById(Long.parseLong(claims.get("id", String.class)));
        return loginUserMapper.toLoginUser(user);
    }

    private Claims getTokenClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
