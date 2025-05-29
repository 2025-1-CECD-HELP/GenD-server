package com.cecd.help.user.presentation.controller;

import com.cecd.help.core.annotation.UserId;
import com.cecd.help.core.common.CommonResponseDto;
import com.cecd.help.user.application.usecase.ReadLoginUserUsecase;
import com.cecd.help.user.application.usecase.UpdateAlarmUseCase;
import com.cecd.help.user.presentation.request.LoginUserRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final ReadLoginUserUsecase readLoginUserUsecase;
    private final UpdateAlarmUseCase updateAlarmUseCase;

    @PostMapping("/login")
    public CommonResponseDto<?> userLogin(
            @RequestBody LoginUserRequestDto loginUserRequestDto
    ) {
        return CommonResponseDto.ok(readLoginUserUsecase.execute(loginUserRequestDto));
    }

    @PatchMapping("/alarms")
    public CommonResponseDto<?> updateAlarm(
            @UserId UUID userId
    ) {
        return CommonResponseDto.ok(updateAlarmUseCase.execute(userId));
    }
}
