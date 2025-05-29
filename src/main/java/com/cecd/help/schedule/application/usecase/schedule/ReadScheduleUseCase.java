package com.cecd.help.schedule.application.usecase.schedule;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.schedule.application.dto.schedule.ReadScheduleListResponseDto;

@UseCase
public interface ReadScheduleUseCase {
    ReadScheduleListResponseDto execute(Long workspaceId);
}
