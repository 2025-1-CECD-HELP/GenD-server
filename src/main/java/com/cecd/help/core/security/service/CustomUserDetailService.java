package com.cecd.help.core.security.service;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.core.security.info.UserPrincipal;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.infrastructure.jpa.UserJpaRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserJpaRepository userRepositoryImpl;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }

    public UserDetails loadUserById(UUID id) {
        User user = userRepositoryImpl.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        return UserPrincipal.create(user);
    }

}
