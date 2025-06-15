package com.cecd.help.user.infrastructure.apple;

import com.cecd.help.core.constant.Constants;
import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.core.security.JwtDto;
import com.cecd.help.core.util.JwtUtil;
import com.cecd.help.core.util.NicknameUtil;
import com.cecd.help.core.util.OAuth2Util;
import com.cecd.help.core.util.PasswordUtil;
import com.cecd.help.user.application.dto.OAuth2UserInfoResponseDto;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.type.ELoginProvider;
import com.cecd.help.user.domain.type.EUserRole;
import com.cecd.help.user.infrastructure.implement.UserRepositoryImpl;
import com.cecd.help.user.infrastructure.jpa.UserJpaRepository;
import com.cecd.help.user.presentation.request.FcmTokenRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthLoginService {
    private final UserRepositoryImpl userRepositoryImpl;
    private final UserJpaRepository userJpaRepository;
    private final OAuth2Util oAuth2Util;
    private final JwtUtil jwtUtil;


    public JwtDto appleLogin(String appleIdToken, FcmTokenRequestDto fcmTokenRequestDto) {
        String fcmToken = fcmTokenRequestDto.fcmToken();
        OAuth2UserInfoResponseDto OAuth2UserInfo = getOAuth2UserInfo(
                ELoginProvider.APPLE.toString(),
                refineToken(appleIdToken)
        );
        User user = userJpaRepository.findBySocialId(OAuth2UserInfo.oAuthId())
                .orElseGet(() -> joinUser(
                        OAuth2UserInfo,
                        ELoginProvider.APPLE,
                        fcmToken
                ));
        user.updateFcmToken(fcmToken);
        return jwtUtil.generateTokens(user.getId(), user.getUserRole());
    }

    private User joinUser(OAuth2UserInfoResponseDto OAuth2UserInfo, ELoginProvider provider, String fcmToken) {
        return userRepositoryImpl.save(
                User.builder()
                        .socialId(OAuth2UserInfo.oAuthId())
                        .nickname(NicknameUtil.generateRandomNickname())
                        .userRole(EUserRole.USER)
                        .provider(provider)
                        .password(PasswordUtil.generateRandomPassword())
                        .fcmToken(fcmToken)
                        .email(OAuth2UserInfo.email())
                        .loginId(OAuth2UserInfo.email())
                        .build()

        );
    }

    private OAuth2UserInfoResponseDto getOAuth2UserInfo(String provider, String accessToken) {
        if (provider.equals(ELoginProvider.APPLE.toString())) {
            return oAuth2Util.getAppleUserInfo(accessToken);
        } else {
            throw new CustomException(ErrorCode.INVALID_OAUTH2_PROVIDER);
        }
    }

    private String refineToken(String accessToken) {
        return accessToken.replaceFirst("^" + Constants.BEARER_PREFIX, "");
    }
}
