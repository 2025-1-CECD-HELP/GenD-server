package com.cecd.help.workspace.presentation.controller;

import com.cecd.help.core.annotation.UserId;
import com.cecd.help.core.common.CommonResponseDto;
import com.cecd.help.workspace.application.usecase.workspace.CreateWorkspaceUseCase;
import com.cecd.help.workspace.application.usecase.workspace.DeleteWorkspaceUseCase;
import com.cecd.help.workspace.application.usecase.workspace.ReadWorkspaceListUseCase;
import com.cecd.help.workspace.application.usecase.workspace.ReadWorkspaceUseCase;
import com.cecd.help.workspace.application.usecase.workspace.UpdateWorkspaceUseCase;
import com.cecd.help.workspace.presentation.request.CreateWorkspaceRequestDto;
import com.cecd.help.workspace.presentation.request.UpdateWorkspaceRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {
    private final CreateWorkspaceUseCase createWorkspaceUseCase;
    private final ReadWorkspaceUseCase readWorkspaceUseCase;
    private final UpdateWorkspaceUseCase updateWorkspaceUseCase;
    private final DeleteWorkspaceUseCase deleteWorkspaceUseCase;
    private final ReadWorkspaceListUseCase readWorkspaceListUseCase;

    @PostMapping("")
    public CommonResponseDto<?> create(
            @RequestPart(name = "image") MultipartFile file,
            @RequestPart(name = "json") CreateWorkspaceRequestDto workspaceRequestDto,
            @UserId UUID userId
    ) {
        createWorkspaceUseCase.execute(file, workspaceRequestDto, userId);
        return CommonResponseDto.created(null);
    }

    @GetMapping("")
    public CommonResponseDto<?> getWorkspaceList(
            @UserId UUID userId
    ) {
        return CommonResponseDto.ok(readWorkspaceListUseCase.execute(userId));
    }

    @GetMapping("/{workspaceId}")
    public CommonResponseDto<?> getWorkspace(
            @PathVariable Long workspaceId,
            @UserId UUID userId
    ) {
        return CommonResponseDto.ok(readWorkspaceUseCase.execute(workspaceId, userId));
    }

    @PatchMapping("/{workspaceId}")
    public CommonResponseDto<?> updateWorkspace(
            @RequestPart(name = "image") MultipartFile file,
            @RequestPart(name = "json") UpdateWorkspaceRequestDto workspaceRequestDto,
            @PathVariable Long workspaceId,
            @UserId UUID userId
    ) {
        updateWorkspaceUseCase.execute(workspaceId, file, workspaceRequestDto, userId);
        return CommonResponseDto.ok(true);
    }

    @DeleteMapping("/{workspaceId}")
    public CommonResponseDto<?> delete(
            @PathVariable Long workspaceId,
            @UserId UUID userId
    ) {
        deleteWorkspaceUseCase.execute(workspaceId, userId);
        return CommonResponseDto.ok(true);
    }
}
