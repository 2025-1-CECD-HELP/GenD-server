package com.cecd.help.user.application.dto;

import com.cecd.help.core.security.JwtDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record LoginUserResponseDto(
        @NotNull(message = "토큰 발급에 실패하였습니다.")
        JwtDto jwtTokenDto,
        @NotNull(message = "다시 로그인해주세요.")
        String userFlag
) {
    @Builder
    public LoginUserResponseDto(JwtDto jwtTokenDto, String userFlag) {
        this.jwtTokenDto = jwtTokenDto;
        this.userFlag = userFlag;
    }

    public static LoginUserResponseDto of(JwtDto jwtTokenDto, String userFlag) {
        return LoginUserResponseDto.builder()
                .jwtTokenDto(jwtTokenDto)
                .userFlag(userFlag)
                .build();
    }
}
