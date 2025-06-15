package com.cecd.help.user.domain.repository;

import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.type.ELoginProvider;
import com.cecd.help.user.infrastructure.jpa.UserJpaRepository.UserSecurityForm;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User findByLoginId(String loginId);
    User findByEmail(String email);
    User findById(UUID id);
    User save(User user);
    User findBySocialId(String socialId);
    void delete(User user);
    Optional<UserSecurityForm> findFormById(UUID id);
    Optional<UserSecurityForm> findFormByIdAndRefreshToken(UUID id, String refreshToken);
    Optional<UserSecurityForm> findFormBySerialIdAndProvider(String serialId, ELoginProvider provider);
    void updateRefreshToken(UUID id, String refreshToken);
    void updateFcmToken(UUID id, String fcmToken);
}
