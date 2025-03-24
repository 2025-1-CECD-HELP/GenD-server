package com.cecd.help.core.util;


import com.cecd.help.core.constant.Constants;
import com.cecd.help.user.application.dto.OAuth2UserInfoResponseDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.jsonwebtoken.Claims;
import java.security.PublicKey;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class OAuth2Util {
    private final RestClient restClient;

    public OAuth2Util() {
        this.restClient = RestClient.builder().build();
    }

    public OAuth2UserInfoResponseDto getKakaoUserInfo(String kakaoAccessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + kakaoAccessToken);
        httpHeaders.add(Constants.CONTENT_TYPE, Constants.APPLICATION_FORM_URLENCODED_WITH_CHARSET);

        try {
            String response = restClient.method(HttpMethod.POST)
                    .uri(Constants.KAKAO_USER_INFO_URI)
                    .headers(headers -> headers.addAll(httpHeaders))
                    .retrieve()
                    .body(String.class);

            if (response == null || response.isEmpty()) {
                throw new RuntimeException("Kakao 유저 정보 요청에 실패했습니다.");
            }

            JsonElement element = JsonParser.parseString(response);

            return OAuth2UserInfoResponseDto.of(
                    element.getAsJsonObject().get("id").getAsString()
            );

        } catch (RestClientException e) {
            log.error("Kakao 유저 정보 요청 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("Kakao 유저 정보 요청 중 오류 발생: " + e.getMessage(), e);
        }
    }


}

