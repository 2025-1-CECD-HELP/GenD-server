package com.cecd.help.workspace.presentation.controller;

import com.cecd.help.core.annotation.UserId;
import com.cecd.help.core.common.CommonResponseDto;
import com.cecd.help.workspace.application.usecase.member.CreateMemberUseCase;
import com.cecd.help.workspace.application.usecase.member.DeleteMemberUseCase;
import com.cecd.help.workspace.application.usecase.member.ReadMemberInfoUseCase;
import com.cecd.help.workspace.application.usecase.member.ReadMemberUseCase;
import com.cecd.help.workspace.application.usecase.member.UpdateMemberAlarmUseCase;
import com.cecd.help.workspace.application.usecase.member.UpdateMemberUseCase;
import com.cecd.help.workspace.presentation.request.InviteEmail;
import com.cecd.help.workspace.presentation.request.UpdateMemberAlarmResponseDto;
import com.cecd.help.workspace.presentation.request.UpdateRoleRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final ReadMemberUseCase readMemberUseCase;
    private final UpdateMemberUseCase updateMemberUseCase;
    private final DeleteMemberUseCase deleteMemberUseCase;
    private final CreateMemberUseCase createMemberUseCase;
    private final UpdateMemberAlarmUseCase updateMemberAlarmUseCase;
    private final ReadMemberInfoUseCase readMemberInfoUseCase;

    @GetMapping("/{workspaceId}")
    public CommonResponseDto<?> readMembers(
            @UserId UUID userId,
            @PathVariable Long workspaceId
    ) {
        return CommonResponseDto.ok(readMemberUseCase.execute(userId, workspaceId));
    }

    @PatchMapping("/{workspaceId}/{memberId}")
    public CommonResponseDto<?> updateMember(
            @UserId UUID userId,
            @PathVariable(name = "memberId") Long memberId,
            @PathVariable(name = "workspaceId") Long workspaceId,
            @RequestBody UpdateRoleRequestDto updateRoleRequestDto
    ) {
        updateMemberUseCase.execute(userId, memberId, workspaceId, updateRoleRequestDto);
        return CommonResponseDto.ok(true);
    }

    @DeleteMapping("/{workspaceId}/{memberId}")
    public CommonResponseDto<?> deleteMember(
            @UserId UUID userId,
            @PathVariable(name = "memberId") Long memberId,
            @PathVariable(name = "workspaceId") Long workspaceId
    ) {
        deleteMemberUseCase.execute(userId, memberId, workspaceId);
        return CommonResponseDto.ok(true);
    }

    @PostMapping("/{workspaceId}")
    public CommonResponseDto<?> createMember(
            @PathVariable(name = "workspaceId") Long workspaceId,
            @RequestBody InviteEmail inviteEmail
            ) {
        return CommonResponseDto.ok(createMemberUseCase.execute(inviteEmail, workspaceId));
    }

    @PatchMapping("/alarm")
    public CommonResponseDto<?> updateAlarm(
            @RequestBody UpdateMemberAlarmResponseDto updateMemberAlarm
    ) {
        return CommonResponseDto.ok(updateMemberAlarmUseCase.execute(updateMemberAlarm));
    }

    @GetMapping("/{workspaceId}/info")
    public CommonResponseDto<?> readMemberInfo(
            @PathVariable(name = "workspaceId") Long workspaceId,
            @UserId UUID userId
    ) {
        return CommonResponseDto.ok(readMemberInfoUseCase.execute(workspaceId, userId));
    }
}
