package com.cecd.help.user.infrastructure.implement;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.type.ELoginProvider;
import com.cecd.help.user.infrastructure.jpa.UserJpaRepository;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.user.infrastructure.jpa.UserJpaRepository.UserSecurityForm;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User findByLoginId(String loginId) {
        return userJpaRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }

    public User findByEmail(String email) {
        return userJpaRepository.findByLoginId(email)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_EMAIL));
    }

    @Override
    public User findById(UUID id) {
        return userJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public void delete(User user) {
        userJpaRepository.delete(user);
    }

    @Override
    public Optional<UserSecurityForm> findFormById(UUID id) {
        return userJpaRepository.findFormById(id);
    }

    @Override
    public Optional<UserSecurityForm> findFormByIdAndRefreshToken(UUID id, String refreshToken) {
        return userJpaRepository.findFormByIdAndRefreshToken(id, refreshToken);
    }

    @Override
    public Optional<UserSecurityForm> findFormBySerialIdAndProvider(String serialId, ELoginProvider provider) {
        return userJpaRepository.findFormBySerialIdAndProvider(serialId, provider);
    }

    @Override
    public void updateRefreshToken(UUID id, String refreshToken) {
        userJpaRepository.updateRefreshToken(id, refreshToken);
    }

    @Override
    public void updateFcmToken(UUID id, String fcmToken) {
        userJpaRepository.updateFcmToken(id, fcmToken);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public User findBySocialId(String socialId) {
        return userJpaRepository.findBySocialId(socialId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }

}
