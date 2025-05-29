package com.cecd.help.user.infrastructure.apple;

public record ApplePublicKey(String kty,
                             String kid,
                             String alg,
                             String n,
                             String e) {
}
