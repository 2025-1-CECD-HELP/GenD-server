package com.cecd.help.user.application.usecase;

import com.cecd.help.user.presentation.request.LoginUserRegisterRequestDto;

public interface CreateLoginUseCase {
    Boolean execute(LoginUserRegisterRequestDto loginUserRegisterRequestDto);
}
