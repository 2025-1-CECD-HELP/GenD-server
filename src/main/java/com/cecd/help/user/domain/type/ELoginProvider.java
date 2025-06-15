package com.cecd.help.user.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ELoginProvider {
    GOOGLE("GOOGLE"),
    KAKAO("KAKAO"),
    NAVER("NAVER"),
    EMAIL("EMAIL"),
    APPLE("APPLE");

    private final String loginProvider;
}
