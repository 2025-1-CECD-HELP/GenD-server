package com.cecd.help.user.presentation.controller;

import com.cecd.help.core.common.CommonResponseDto;
import com.cecd.help.core.constant.Constants;
import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.core.util.HeaderUtil;
import com.cecd.help.user.application.usecase.oauth.GetTokenByKakaoUseCase;
import com.cecd.help.user.application.usecase.oauth.LoginByKakaoUseCase;
import com.cecd.help.user.application.usecase.oauth.RedirectToKakaoLoginUseCase;
import com.cecd.help.user.infrastructure.apple.AuthLoginService;
import com.cecd.help.user.presentation.request.FcmTokenRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {
    private final LoginByKakaoUseCase loginByKakaoUseCase;
    private final RedirectToKakaoLoginUseCase redirectToKakaoLoginUseCase;
    private final GetTokenByKakaoUseCase getTokenByKakaoUseCase;
    private final AuthLoginService authLoginService;

    @PostMapping("/login/kakao")
    public CommonResponseDto<?> loginByKakao(
            HttpServletRequest request,
            @RequestBody FcmTokenRequestDto fcmTokenRequestDto
    ) {
        String accessToken = HeaderUtil.refineHeader(request, Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_AUTHORIZATION_HEADER));

        return CommonResponseDto.ok(loginByKakaoUseCase.execute(accessToken, fcmTokenRequestDto));
    }

    @GetMapping("/login/kakao")
    public ResponseEntity<?> loginByKakao(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        // redirectToKakaoLoginUseCase로 리다이렉트 URL을 받아온다.
        String redirectUrl = redirectToKakaoLoginUseCase.execute();

        // ResponseEntity로 리다이렉트 URL을 반환한다.
        return ResponseEntity.status(302).header("Location", redirectUrl).build();
    }

    @GetMapping("/login/kakao/callback")
    public CommonResponseDto<?> callbackByKakao(
            @RequestParam("code") String authorizationCode
    ) {
        return CommonResponseDto.ok(getTokenByKakaoUseCase.execute(authorizationCode));
    }

    @PostMapping("/login/apple")
    public CommonResponseDto<?> appleLogin(
            @NotNull @RequestHeader(Constants.AUTHORIZATION_HEADER) String appleIdToken,
            @RequestBody FcmTokenRequestDto fcmTokenRequestDto
    ) {
        log.info("Apple authToken : {}", appleIdToken);
        return CommonResponseDto.ok(authLoginService.appleLogin(appleIdToken, fcmTokenRequestDto));
    }
}
