package com.ssafy.a403.config.security.user.service;

import com.ssafy.a403.config.security.user.dao.UserRepository;
import com.ssafy.a403.config.security.user.entity.Oauth2;
import com.ssafy.a403.config.security.user.entity.User;
import com.ssafy.a403.config.security.oauth.OAuth2Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public User saveIfNotExists(OAuth2Request oAuth2Request) {
        return userRepository.findByOauth2AccountId(oAuth2Request.getAccountId())
                .orElseGet(() -> save(oAuth2Request));
    }

    private User save(OAuth2Request oAuth2Request) {
        return userRepository.save(new User(
                oAuth2Request.getName(),
                new Oauth2(oAuth2Request.getAuthProvider(), oAuth2Request.getAccountId())
                )
        );
    }
}
