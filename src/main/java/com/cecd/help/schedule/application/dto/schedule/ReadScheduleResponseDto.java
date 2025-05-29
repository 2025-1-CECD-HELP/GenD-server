package com.cecd.help.schedule.application.dto.schedule;

import com.cecd.help.schedule.domain.type.EScheType;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ReadScheduleResponseDto(
        Long scheduleId,
        String scheduleTitle,
        String startSchedule,
        String endSchedule,
        String startAlarm,
        EScheType type,
        String scheduleDescription
) {

}

/*
"scheduleId": 1,
						    "scheduleTitle": "알림 제목",
						    "isAlarm": true,
						    "startSchedule": "2025-05-04T17:30",
						    "endSchedule": "2025-05-04T18:30",
						    "startAlarm": "2025-05-04T17:22",
						    "type": " [Study | Presentation | Meeting | Activity]"
						    "scheduleDescription": "스케쥴러 알림메세지"
						},
 */