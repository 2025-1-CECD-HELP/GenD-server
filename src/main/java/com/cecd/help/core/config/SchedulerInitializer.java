//package com.cecd.help.core.config;
//
//import com.cecd.help.core.batch.DynamicTaskScheduler;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class SchedulerInitializer implements CommandLineRunner {
//    private final DynamicTaskScheduler dynamicTaskScheduler;
//
//    @Override
//    public void run(String... args) {
//        dynamicTaskScheduler.scheduleAttendanceTasks();
//        log.info("사용자 출근 스케줄 작업이 초기화되었습니다.");
//    }
//}
//
