package com.cecd.help.core.batch;

import com.cecd.help.core.util.FcmUtil;
import com.cecd.help.schedule.domain.entity.Schedule;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DynamicTaskScheduler {

    private final ThreadPoolTaskScheduler taskScheduler;
    private final MemberRepository memberRepository;
    private final FcmUtil fcmUtil;

    private final Map<UUID, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    @Transactional
    public void scheduleSingleUserTask(Workspace workspace, Schedule schedule) {
        // 1. Workspace받아서 member들 모으기
        List<Member> members = memberRepository.findAllByWorkspaceAndIsSchedule(workspace, true);

        // 2, 딜레이 계산하기
        long delay = calculateDelay(LocalTime.from(schedule.getStartAlarm().plusHours(-9L)));
        String title = schedule.getType().toString();
        Long workspaceId = workspace.getId();
        // 3.
        String message = schedule.getScheduleDescription();
        members.forEach(member -> scheduleTask(member, delay, title, message, member.getUser().getFcmToken(),  workspaceId));
    }

    private void scheduleTask(Member member, long delay, String title, String message, String token, Long workspaceId) {
        ScheduledFuture<?> future = taskScheduler.schedule(
                () -> fcmUtil.sendMessage(title, message, token, workspaceId),
                new Date(System.currentTimeMillis() + delay)
        );

        ScheduledFuture<?> existingTask = scheduledTasks.put(member.getUser().getId(), future);
        if (existingTask != null) {
            existingTask.cancel(false);
            log.info("기존 작업 취소: 사용자 {}", member.getUser().getNickname());
        }

        log.info("스케줄링 예약: 사용자 {}, 지연: {} ms", member.getUser().getNickname(), delay);
    }

    private long calculateDelay(LocalTime attendanceTime) {
        long delay = Duration.between(LocalTime.now(), attendanceTime).toMillis();
        return delay >= 0 ? delay : TimeUnit.DAYS.toMillis(1) + delay; // 다음 날로 예약
    }




//    @Transactional
//    public void scheduleAttendanceTasks() {
//        List<User> activeUsers = getActiveUsers();
//        Map<UUID, Map<EChatType, ChatRoom>> preloadedChatRooms = preloadChatRooms(activeUsers);
//
//        activeUsers.forEach(user -> scheduleTask(user, preloadedChatRooms));
//        logScheduledTasks();
//    }

//    private List<User> getActiveUsers() {
//        return userRepository.findAll().stream()
//                .filter(user -> user.getAttendanceTime() != null && user.getWorkEndTime().isAfter(LocalDate.now()))
//                .toList();
//    }
//
//    private Map<UUID, Map<EChatType, ChatRoom>> preloadChatRooms(List<User> users) {
//        List<ChatRoom> chatRooms = chatRoomRepository.findAllByUserInAndChatType(users, EChatType.FOLD);
//        return chatRooms.stream()
//                .collect(Collectors.groupingBy(
//                        chatRoom -> chatRoom.getUser().getId(),
//                        Collectors.toMap(
//                                ChatRoom::getChatType,
//                                Function.identity()
//                        )
//                ));
//    }


//    private void sendAttendanceMessages(User user, Map<UUID, Map<EChatType, ChatRoom>> preloadedChatRooms) {
//        log.info("출근 메시지 전송: 사용자 {}", user.getNickname());
//        List<String> messages = createMessages(user);
//        messages.forEach(message -> sendMessage(user, message, preloadedChatRooms));
//    }
//
//    private List<String> createMessages(User user) {
//        String nickname = user.getNickname();
//        String startTime = user.getAttendanceTime().format(DateTimeFormatter.ofPattern("HH:mm"));
//        String endTime = user.getAttendanceTime().plusHours(1).format(DateTimeFormatter.ofPattern("HH:mm"));
//
//        return List.of(
//                String.format("안녕하세요 %s 인턴님! 오늘도 좋은 하루를 시작하기 위해 첫 업무를 안내드립니다.", nickname),
//                String.format("업무: 침대 정리하기(%s ~ %s)", startTime, endTime),
//                "업무 공간을 깔끔하게 유지하는 것은 업무 효율을 높이는 첫걸음입니다. 출근 준비를 하시고 깨끗한 환경에서 업무를 시작해 주세요! 출근 준비가 된 후 책상에 앉으신 다음에 업무 보고 부탁드립니다. 오늘도 멋진 하루 되시길 바랍니다!",
//                "오늘도 좋은 하루 되세요!"
//        );
//    }
//
//
//    private void sendMessage(User user, String message, Map<UUID, Map<EChatType, ChatRoom>> preloadedChatRooms) {
//        ChatRoom chatRoom = getOrCreateChatRoom(user, preloadedChatRooms);
//        chatRepository.save(createChat(chatRoom, message));
//    }
//
//    private ChatRoom getOrCreateChatRoom(User user, Map<UUID, Map<EChatType, ChatRoom>> preloadedChatRooms) {
//        return preloadedChatRooms
//                .computeIfAbsent(user.getId(), id -> new HashMap<>())
//                .computeIfAbsent(EChatType.FOLD, type -> createChatRoom(user));
//    }
//
//    private ChatRoom createChatRoom(User user) {
//        return chatRoomRepository.save(
//                ChatRoom.builder()
//                        .user(user)
//                        .chatType(EChatType.FOLD)
//                        .title("일상회복팀 리부트대리")
//                        .build()
//        );
//    }
//
//    private Chat createChat(ChatRoom chatRoom, String message) {
//        return Chat.builder()
//                .chatRoomId(chatRoom.getId())
//                .responseContent(message)
//                .speaker(ESpeaker.AI)
//                .isRead(false)
//                .build();
//    }
//
//    private void logScheduledTasks() {
//        scheduledTasks.forEach((userId, task) ->
//                log.info("사용자 ID: {}, 상태: {}", userId, task.isCancelled() ? "취소됨" : "활성화됨")
//        );
//        if (scheduledTasks.isEmpty()) {
//            log.info("예약된 작업이 없습니다.");
//        }
//    }
}
