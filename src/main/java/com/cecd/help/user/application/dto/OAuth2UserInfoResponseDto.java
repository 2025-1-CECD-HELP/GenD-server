package com.cecd.help.user.application.dto;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record OAuth2UserInfoResponseDto(
        String oAuthId,
        String email// 애플 sub, 카카오 id
) {
    public static OAuth2UserInfoResponseDto of(String oAuthId, String email) {
        return OAuth2UserInfoResponseDto.builder()
                .oAuthId(oAuthId)
                .email(email)
                .build();
    }
}
