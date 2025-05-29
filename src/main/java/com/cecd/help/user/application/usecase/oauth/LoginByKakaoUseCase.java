package com.cecd.help.user.application.usecase.oauth;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.core.security.JwtDto;
import com.cecd.help.user.application.dto.LoginUserResponseDto;
import com.cecd.help.user.presentation.request.FcmTokenRequestDto;

@UseCase
public interface LoginByKakaoUseCase {
    JwtDto execute(String accessToken, FcmTokenRequestDto fcmTokenRequestDto);
}
