package com.cecd.help.schedule.presentation.controller;

import com.cecd.help.core.annotation.UserId;
import com.cecd.help.core.common.CommonResponseDto;
import com.cecd.help.schedule.application.usecase.schedule.CreateScheduleUseCase;
import com.cecd.help.schedule.application.usecase.schedule.ReadScheduleUseCase;
import com.cecd.help.schedule.presentation.request.CreateScheduleRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedule")
public class ScheduleController {
    private final CreateScheduleUseCase createScheduleUseCase;
    private final ReadScheduleUseCase readScheduleUseCase;

    @PostMapping("/{workspaceId}")
    public CommonResponseDto<?> createSchedule(
            @UserId UUID userId,
            @RequestBody CreateScheduleRequestDto createScheduleRequestDto,
            @PathVariable(name = "workspaceId") Long workspaceId
    ) {
        return CommonResponseDto.ok(createScheduleUseCase.execute(createScheduleRequestDto, workspaceId, userId));
    }

    @GetMapping("/{workspaceId}")
    public CommonResponseDto<?> readSchedule(
            @PathVariable(name = "workspaceId") Long workspaceId
    ) {
        return CommonResponseDto.ok(readScheduleUseCase.execute(workspaceId));
    }
}
