package com.cecd.help.schedule.application.usecase.schedule;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.schedule.presentation.request.CreateScheduleRequestDto;
import java.util.UUID;

@UseCase
public interface CreateScheduleUseCase {
    Boolean execute(CreateScheduleRequestDto createScheduleRequestDto, Long workspaceId, UUID userId);
}
