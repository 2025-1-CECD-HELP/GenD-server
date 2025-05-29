package com.cecd.help.user.application.usecase.oauth;

import com.cecd.help.core.annotation.UseCase;

@UseCase
public interface GetTokenByKakaoUseCase {
    String execute(String authorizationCode);
}
