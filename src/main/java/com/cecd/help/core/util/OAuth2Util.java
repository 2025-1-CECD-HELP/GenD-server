package com.cecd.help.core.util;


import com.cecd.help.core.constant.Constants;
import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.user.application.dto.OAuth2UserInfoResponseDto;
import com.cecd.help.user.infrastructure.apple.AppleJwtParser;
import com.cecd.help.user.infrastructure.apple.ApplePublicKeyGenerator;
import com.cecd.help.user.infrastructure.apple.ApplePublicKeyResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.jsonwebtoken.Claims;
import java.security.PublicKey;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class OAuth2Util {
    @Value("${spring.security.oauth2.client.provider.kakao.authorization-uri}")
    private String KAKAO_AUTHORIZATION_URL;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String KAKAO_TOKEN_URL;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String KAKAO_USERINFO_URL;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URL;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String KAKAO_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET;

    private RestClient restClient = RestClient.create();

    public OAuth2Util(AppleJwtParser appleJwtParser, ApplePublicKeyGenerator applePublicKeyGenerator) {
        this.appleJwtParser = appleJwtParser;
        this.applePublicKeyGenerator = applePublicKeyGenerator;
        this.restClient = RestClient.builder().build();
    }

    public String getKakaoRedirectUrl() {
        return KAKAO_AUTHORIZATION_URL
                + "?client_id=" + KAKAO_CLIENT_ID +
                "&redirect_uri=" + KAKAO_REDIRECT_URL +
                "&response_type=code";
    }

    public String getKakaoAccessToken(String authorizationCode) {
        Map<String, Object> response;

        try {
            response = Objects.requireNonNull(restClient.post()
                    .uri(KAKAO_TOKEN_URL)
                    .headers(httpHeaders -> {
                        httpHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                    })
                    .body("grant_type=authorization_code&client_id=" + KAKAO_CLIENT_ID + "&client_secret=" + KAKAO_CLIENT_SECRET + "&code=" + authorizationCode)
                    .retrieve()
                    .toEntity(Map.class).getBody());
        } catch (Exception e) {
            throw new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR);
        }

        return response.get("access_token").toString();
    }

    public Map<String, String> getKakaoUserInformation(String accessToken) {
        Map<String, Object> response;

        try {
            response = Objects.requireNonNull(restClient.post()
                    .uri(KAKAO_USERINFO_URL)
                    .headers(httpHeaders -> {
                        httpHeaders.setBearerAuth(accessToken);
                        httpHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                    })
                    .retrieve()
                    .toEntity(Map.class).getBody());
        } catch (Exception e) {
            throw new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR);
        }

        Map<String, Object> kakaoAccount = (Map<String, Object>) response.get("kakao_account");
        Map<String, String> profile = (Map<String, String>) kakaoAccount.get("profile");

        String id = response.get("id").toString();
        String nickname = profile.get("nickname");
        String email = kakaoAccount.get("email").toString();

        if (response.get("id") == null || response.get("properties") == null)
            throw new CustomException(ErrorCode.EXTERNAL_SERVER_ERROR);

        return Map.of(
                "id", id,
                "nickname", nickname,
                "email", email
        );
    }

    private final RestTemplate restTemplate = new RestTemplate();
    private final AppleJwtParser appleJwtParser;
    private final ApplePublicKeyGenerator applePublicKeyGenerator;

    public OAuth2UserInfoResponseDto getAppleUserInfo(String appleIdToken) {
        Map<String, String> headers = appleJwtParser.parseHeaders(appleIdToken);
        ResponseEntity<ApplePublicKeyResponse> applePublicKeys = restTemplate.getForEntity(
                Constants.APPLE_PUBLIC_KEYS_URL,
                ApplePublicKeyResponse.class
        );
        PublicKey publicKey = applePublicKeyGenerator.generatePublicKey(headers, applePublicKeys.getBody());
        Claims claims = appleJwtParser.parseClaims(appleIdToken, publicKey);

        System.err.println(claims);

        return OAuth2UserInfoResponseDto.of(
                claims.get("sub", String.class),
                claims.get("email", String.class)
        );
    }
}

