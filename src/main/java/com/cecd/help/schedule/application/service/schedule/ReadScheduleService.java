package com.cecd.help.schedule.application.service.schedule;

import com.cecd.help.schedule.application.dto.schedule.ReadScheduleListResponseDto;
import com.cecd.help.schedule.application.dto.schedule.ReadScheduleResponseDto;
import com.cecd.help.schedule.application.usecase.schedule.ReadScheduleUseCase;
import com.cecd.help.schedule.domain.entity.Schedule;
import com.cecd.help.schedule.domain.repository.ScheduleRepository;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadScheduleService implements ReadScheduleUseCase {
    private final ScheduleRepository scheduleRepository;
    private final WorkspaceRepository workspaceRepository;

    @Override
    public ReadScheduleListResponseDto execute(Long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId);

        List<Schedule> schedule = scheduleRepository.findByWorkspace(workspace);

        if(schedule == null)
            return null;

        List<ReadScheduleResponseDto> scheduleResponseDtos = schedule.stream().map(
                schedule1 -> ReadScheduleResponseDto.builder()
                        .scheduleId(schedule1.getId())
                        .scheduleTitle(schedule1.getScheduleTitle())
                        .scheduleDescription(schedule1.getScheduleDescription())
                        .startSchedule(schedule1.getStartSchedule().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")))
                        .endSchedule(schedule1.getEndSchedule().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")))
                        .startAlarm(schedule1.getStartAlarm().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")))
                        .type(schedule1.getType())
                        .build()
        ).toList();

        return ReadScheduleListResponseDto.builder()
                .scheduleList(scheduleResponseDtos)
                .build();

    }
}
