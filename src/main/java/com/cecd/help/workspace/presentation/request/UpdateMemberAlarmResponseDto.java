package com.cecd.help.workspace.presentation.request;

public record UpdateMemberAlarmResponseDto(
        Long memberId,
        Boolean isPost,
        Boolean isSchedule
) {
}
