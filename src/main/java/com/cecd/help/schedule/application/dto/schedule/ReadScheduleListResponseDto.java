package com.cecd.help.schedule.application.dto.schedule;

import java.util.List;
import lombok.Builder;

@Builder
public record ReadScheduleListResponseDto(
        List<ReadScheduleResponseDto> scheduleList
) {
}
