package com.cecd.help.schedule.application.service.schedule;

import com.cecd.help.core.batch.DynamicTaskScheduler;
import com.cecd.help.schedule.application.mapper.ScheduleMapper;
import com.cecd.help.schedule.application.usecase.schedule.CreateScheduleUseCase;
import com.cecd.help.schedule.domain.entity.Schedule;
import com.cecd.help.schedule.domain.repository.ScheduleRepository;
import com.cecd.help.schedule.presentation.request.CreateScheduleRequestDto;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CreateScheduleService implements CreateScheduleUseCase {
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final ScheduleMapper scheduleMapper;
    private final ScheduleRepository scheduleRepository;
    private final DynamicTaskScheduler dynamicTaskScheduler;

    @Override // admin
    public Boolean execute(CreateScheduleRequestDto createScheduleRequestDto, Long workspaceId, UUID userId) {
        User user = userRepository.findById(userId);
        Workspace workspace = workspaceRepository.findById(workspaceId);

        log.info(createScheduleRequestDto.startSchedule().toString());

        Schedule newSchedule = scheduleMapper.toEntity(
                createScheduleRequestDto.scheduleTitle(),
                createScheduleRequestDto.isAlarm(),
                createScheduleRequestDto.startSchedule(),
                createScheduleRequestDto.endSchedule(),
                createScheduleRequestDto.startAlarm(),
                createScheduleRequestDto.type(),
                createScheduleRequestDto.scheduleDescription(),
                workspace,
                user
        );
        scheduleRepository.save(newSchedule);

        if(createScheduleRequestDto.isAlarm())
            dynamicTaskScheduler.scheduleSingleUserTask(workspace, newSchedule);
        return true;
    }
}
