package com.cecd.help.schedule.infrastructure.jpa;

import com.cecd.help.schedule.domain.entity.Schedule;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleJpaRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByWorkspace(Workspace workspace);
}
