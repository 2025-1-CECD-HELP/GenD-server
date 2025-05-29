package com.cecd.help.user.application.service.oauth;

import com.cecd.help.core.util.OAuth2Util;
import com.cecd.help.user.application.usecase.oauth.GetTokenByKakaoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTokenByKakaoService implements GetTokenByKakaoUseCase {
    private final OAuth2Util oAuth2Util;

    @Override
    public String execute(String authorizationCode) {
        return oAuth2Util.getKakaoAccessToken(authorizationCode);
    }
}
