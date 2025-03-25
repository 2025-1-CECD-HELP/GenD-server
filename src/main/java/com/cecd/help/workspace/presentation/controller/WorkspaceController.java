package com.cecd.help.workspace.presentation.controller;

import com.cecd.help.core.annotation.UserId;
import com.cecd.help.core.common.CommonResponseDto;
import com.cecd.help.workspace.application.usecase.CreateWorkspaceUseCase;
import com.cecd.help.workspace.presentation.request.CreateWorkspaceRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {
    private final CreateWorkspaceUseCase createWorkspaceUseCase;

    @PostMapping("")
    public CommonResponseDto<?> create(
            @RequestBody CreateWorkspaceRequestDto workspaceRequestDto,
            @UserId UUID userId
    ) {
        createWorkspaceUseCase.execute(workspaceRequestDto, userId);
        return CommonResponseDto.created(null);
    }
}
