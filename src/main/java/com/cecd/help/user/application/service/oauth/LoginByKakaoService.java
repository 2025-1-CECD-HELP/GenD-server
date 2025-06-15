package com.cecd.help.user.application.service.oauth;

import com.cecd.help.core.constant.Constants;
import com.cecd.help.core.security.JwtDto;
import com.cecd.help.core.util.JwtUtil;
import com.cecd.help.core.util.OAuth2Util;
import com.cecd.help.core.util.PasswordUtil;
import com.cecd.help.user.application.dto.LoginUserResponseDto;
import com.cecd.help.user.application.usecase.oauth.LoginByKakaoUseCase;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.user.domain.type.ELoginProvider;
import com.cecd.help.user.domain.type.EUserRole;
import com.cecd.help.user.infrastructure.jpa.UserJpaRepository;
import com.cecd.help.user.presentation.request.FcmTokenRequestDto;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginByKakaoService implements LoginByKakaoUseCase {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final OAuth2Util oAuth2Util;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public JwtDto execute(String accessToken, FcmTokenRequestDto fcmTokenRequestDto) {
        Map<String, String> userInfo = oAuth2Util.getKakaoUserInformation(accessToken);

        String serialId = userInfo.get("id");
        UserJpaRepository.UserSecurityForm userSecurityForm = userRepository.findFormBySerialIdAndProvider(serialId, ELoginProvider.KAKAO)
                .orElseGet(() -> {
                    User user = userRepository.save(
                            User.builder()
                                    .socialId(serialId)
                                    .provider(ELoginProvider.KAKAO)
                                    .userRole(EUserRole.USER)
                                    .nickname(userInfo.get("nickname"))
                                    .email(userInfo.get("email"))
                                    .loginId(userInfo.get("email"))
                                    .fcmToken(fcmTokenRequestDto.fcmToken())
                                    .password(bCryptPasswordEncoder.encode(PasswordUtil.generateRandomPassword()))
                                    .build()
                    );
                    return UserJpaRepository.UserSecurityForm.of(user);
                });

        JwtDto jwtTokenDto = jwtUtil.generateTokens(
                userSecurityForm.getId(),
                userSecurityForm.getRole()
        );

        userRepository.updateRefreshToken(userSecurityForm.getId(), jwtTokenDto.refreshToken());
        userRepository.updateFcmToken(userSecurityForm.getId(), fcmTokenRequestDto.fcmToken());

        return jwtTokenDto;
    }
}
