package com.cecd.help.user.infrastructure.apple;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import java.util.List;
import org.springframework.security.core.AuthenticationException;

public record ApplePublicKeyResponse(List<ApplePublicKey> keys) {
    public ApplePublicKey getMatchedKey(String kid, String alg) throws AuthenticationException {
        return keys.stream()
                .filter(key -> key.kid().equals(kid) && key.alg().equals(alg))
                .findAny()
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_APPLE_PUBLIC_KEY_ERROR));
    }
}
