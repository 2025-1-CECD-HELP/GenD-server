package com.cecd.help.user.application.service;

import com.cecd.help.user.application.usecase.CreateLoginUseCase;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.user.domain.type.ELoginProvider;
import com.cecd.help.user.domain.type.EUserRole;
import com.cecd.help.user.presentation.request.LoginUserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateLoginService implements CreateLoginUseCase {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Boolean execute(LoginUserRegisterRequestDto loginUserRegisterRequestDto) {

        User user = User.builder()
                .socialId(loginUserRegisterRequestDto.loginId())
                .email(loginUserRegisterRequestDto.loginId())
                .loginId(loginUserRegisterRequestDto.loginId())
                .nickname(loginUserRegisterRequestDto.nickname())
                .userRole(EUserRole.USER)
                .password(passwordEncoder.encode(loginUserRegisterRequestDto.password()))
                .provider(ELoginProvider.EMAIL)
                .build();

        userRepository.save(user);
        return true;

    }

}
