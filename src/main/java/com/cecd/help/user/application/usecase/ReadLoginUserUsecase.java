package com.cecd.help.user.application.usecase;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.core.security.JwtDto;
import com.cecd.help.user.presentation.request.LoginUserRequestDto;

@UseCase
public interface ReadLoginUserUsecase {
    JwtDto execute(LoginUserRequestDto loginUserRequestDto);
}
