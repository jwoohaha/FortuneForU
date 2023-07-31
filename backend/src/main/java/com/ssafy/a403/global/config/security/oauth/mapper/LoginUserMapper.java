package com.ssafy.a403.global.config.security.oauth.mapper;

import com.ssafy.a403.global.config.security.LoginUser;
import com.ssafy.a403.global.config.security.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class LoginUserMapper {
	
	public LoginUser toLoginUser(User user) {
        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("id", user.getId());
        return new LoginUser(user, attributes, user.getAuthorityRole());
    }

}
