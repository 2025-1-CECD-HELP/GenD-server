package com.cecd.help.document.presentation.controller;

import com.cecd.help.core.common.CommonResponseDto;
import com.cecd.help.document.application.usecase.directory.CreateDirectoryUseCase;
import com.cecd.help.document.application.usecase.directory.DeleteDirectoryUseCase;
import com.cecd.help.document.application.usecase.directory.ReadDirectoryUseCase;
import com.cecd.help.document.application.usecase.directory.UpdateDirectoryUseCase;
import com.cecd.help.document.presentation.request.DirectoryRequestDto;
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
@RequestMapping("/api/v1/directory")
@RequiredArgsConstructor
public class DirectoryController {
    private final ReadDirectoryUseCase readDirectoryUseCase;
    private final CreateDirectoryUseCase createDirectoryUseCase;
    private final UpdateDirectoryUseCase updateDirectoryUseCase;
    private final DeleteDirectoryUseCase deleteDirectoryUseCase;


    @GetMapping("/{workspaceId}")
    public CommonResponseDto<?> readList(
            @PathVariable(name = "workspaceId") Long workspaceId
    ) {
        return CommonResponseDto.ok(readDirectoryUseCase.execute(workspaceId));
    }

    @PostMapping("/{workspaceId}/{parentId}")
    public CommonResponseDto<?> create(
            @RequestBody DirectoryRequestDto createDirectoryRequestDto,
            @PathVariable(name = "workspaceId") Long workspaceId,
            @PathVariable(name = "parentId") Long parentId
    ) {
        return CommonResponseDto.ok(createDirectoryUseCase.execute(createDirectoryRequestDto, workspaceId, parentId));
    }

    @PatchMapping("/{dirId}")
    public CommonResponseDto<?> updateDir(
            @RequestBody DirectoryRequestDto directoryRequestDto,
            @PathVariable(name = "dirId") Long dirId
    ) {
        return CommonResponseDto.ok(updateDirectoryUseCase.execute(directoryRequestDto, dirId));
    }

    @DeleteMapping("/{dirId}")
    public CommonResponseDto<?> delete(
            @PathVariable(name = "dirId") Long dirId
    ) {
        return CommonResponseDto.ok(deleteDirectoryUseCase.execute(dirId));
    }
}
