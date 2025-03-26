package com.cecd.help.user.application.service;

import com.cecd.help.core.security.JwtDto;
import com.cecd.help.core.util.JwtUtil;
import com.cecd.help.user.application.usecase.ReadLoginUserUsecase;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.type.EUserRole;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.user.presentation.request.LoginUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReadLoginUserService implements ReadLoginUserUsecase {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public JwtDto execute(LoginUserRequestDto loginUserRequestDto) {
        User user = userRepository.findByLoginId(loginUserRequestDto.loginId());

        return jwtUtil.generateTokens(user.getId(), EUserRole.USER);

    }
}
