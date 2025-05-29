package com.cecd.help.schedule.domain.repository;

import com.cecd.help.schedule.domain.entity.Schedule;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository {
    void save(Schedule schedule);
    List<Schedule> findByWorkspace(Workspace workspace);
}
