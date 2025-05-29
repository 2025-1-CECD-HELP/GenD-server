package com.cecd.help.schedule.infrastructure.implement;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.schedule.domain.entity.Schedule;
import com.cecd.help.schedule.domain.repository.ScheduleRepository;
import com.cecd.help.schedule.infrastructure.jpa.ScheduleJpaRepository;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final ScheduleJpaRepository scheduleJpaRepository;

    @Override
    public void save(Schedule schedule) {
        scheduleJpaRepository.save(schedule);
    }

    @Override
    public List<Schedule> findByWorkspace(Workspace workspace) {
        return scheduleJpaRepository.findByWorkspace(workspace);
    }
}
