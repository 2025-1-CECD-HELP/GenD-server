package com.cecd.help.schedule.presentation.request;

import com.cecd.help.schedule.domain.type.EScheType;
import java.time.LocalDateTime;

public record CreateScheduleRequestDto(
        String scheduleTitle,
        Boolean isAlarm,
        LocalDateTime startSchedule,
        LocalDateTime endSchedule,
        LocalDateTime startAlarm,
        EScheType type,
        String scheduleDescription
) {
}
