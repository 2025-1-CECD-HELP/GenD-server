package com.cecd.help.schedule.domain.entity;

import com.cecd.help.schedule.domain.type.EScheType;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "schedule_title")
    private String scheduleTitle;

    @Column(nullable = false, name = "is_alarm")
    private Boolean isAlarm;

    @Column(nullable = false, name = "start_schedule")
    private LocalDateTime startSchedule;

    @Column(nullable = false, name = "end_schedule")
    private LocalDateTime endSchedule;

    @Column(nullable = false, name = "start_alarm")
    private LocalDateTime startAlarm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "type")
    private EScheType type;

    @Column(name = "schedule_description")
    private String scheduleDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "workspace_id")
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Builder
    public Schedule(String scheduleTitle, Boolean isAlarm, LocalDateTime startSchedule, LocalDateTime endSchedule, LocalDateTime startAlarm, EScheType type, String scheduleDescription,  Workspace workspace, User user) {
        this.scheduleTitle = scheduleTitle;
        this.isAlarm = isAlarm;
        this.startSchedule = startSchedule;
        this.endSchedule = endSchedule;
        this.startAlarm = startAlarm;
        this.type = type;
        this.scheduleDescription = scheduleDescription;
        this.workspace = workspace;
        this.user = user;
    }




}
