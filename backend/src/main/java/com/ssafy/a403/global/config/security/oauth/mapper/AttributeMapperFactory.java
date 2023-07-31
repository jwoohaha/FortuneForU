package com.ssafy.a403.global.config.security.oauth.mapper;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.EnumMap;

@Component
public class AttributeMapperFactory {
    private final Map<AuthProvider, AttributeMapper> mapperMap =
            new EnumMap<>(AuthProvider.class);
    private final GoogleAttributeMapper googleAttributeMapper;

    public AttributeMapperFactory(GoogleAttributeMapper googleAttributeMapper) {
        this.googleAttributeMapper = googleAttributeMapper;
        initialize();
    }

    private void initialize() {
        mapperMap.put(AuthProvider.GOOGLE, googleAttributeMapper);
    }

    public AttributeMapper getAttributeMapper(AuthProvider authProvider) {
        return mapperMap.get(authProvider);
    }
}
