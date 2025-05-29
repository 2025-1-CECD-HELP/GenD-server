package com.cecd.help.user.application.service.oauth;

import com.cecd.help.core.util.OAuth2Util;
import com.cecd.help.user.application.usecase.oauth.RedirectToKakaoLoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedirectToKakaoLoginService implements RedirectToKakaoLoginUseCase {
    private final OAuth2Util oAuth2Util;

    @Override
    public String execute() {
        return oAuth2Util.getKakaoRedirectUrl();
    }
}
