package com.cecd.help.workspace.presentation.controller;

import com.cecd.help.core.annotation.UserId;
import com.cecd.help.core.common.CommonResponseDto;
import com.cecd.help.workspace.application.usecase.member.ReadMemberUseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final ReadMemberUseCase readMemberUseCase;

    @GetMapping("/{workspaceId}")
    public CommonResponseDto<?> readMembers(
            @UserId UUID userId,
            @PathVariable Long workspaceId
    ) {
        return CommonResponseDto.ok(readMemberUseCase.execute(userId, workspaceId));
    }
}
