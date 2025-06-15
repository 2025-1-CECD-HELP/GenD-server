package com.cecd.help.schedule.application.mapper;

import com.cecd.help.schedule.domain.entity.Schedule;
import com.cecd.help.schedule.domain.type.EScheType;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleMapper {

    public Schedule toEntity(String scheduleTitle,
                             Boolean isAlarm,
                             LocalDateTime startSchedule,
                             LocalDateTime endSchedule,
                             LocalDateTime startAlarm,
                             EScheType type,
                             String scheduleDescription,
                             Workspace workspace,
                             User user
    ) {
        return Schedule.builder()
                .scheduleTitle(scheduleTitle)
                .isAlarm(isAlarm)
                .startSchedule(startSchedule.plusHours(0L))
                .endSchedule(endSchedule.plusHours(0L))
                .startAlarm(startAlarm.plusHours(0L))
                .type(type)
                .scheduleDescription(scheduleDescription)
                .workspace(workspace)
                .user(user)
                .build();

    }
}
