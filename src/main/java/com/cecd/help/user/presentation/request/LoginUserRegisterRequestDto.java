package com.cecd.help.user.presentation.request;

public record LoginUserRegisterRequestDto(
        String nickname,
        String loginId,
        String password
) {
}
