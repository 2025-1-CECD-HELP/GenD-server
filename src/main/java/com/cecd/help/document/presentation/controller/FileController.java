package com.cecd.help.document.presentation.controller;

import com.cecd.help.core.common.CommonResponseDto;
import com.cecd.help.document.application.usecase.file.CreateFileUseCase;
import com.cecd.help.document.application.usecase.file.DeleteFileUseCase;
import com.cecd.help.document.application.usecase.file.ReadFileUseCase;
import com.cecd.help.document.application.usecase.file.UpdateFileUseCase;
import com.cecd.help.document.presentation.request.UpdateFileRequestDto;
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
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final CreateFileUseCase createFileUseCase;
    private final UpdateFileUseCase updateFileUseCase;
    private final DeleteFileUseCase deleteFileUseCase;
    private final ReadFileUseCase readFileUseCase;

    @GetMapping("/{workspaceId}/{dirId}")
    public CommonResponseDto<?> readList(
            @PathVariable(name = "workspaceId") Long workspaceId,
            @PathVariable(name = "dirId") Long dirId
    ) {
        return CommonResponseDto.ok(readFileUseCase.execute(workspaceId, dirId));
    }

    @PostMapping("/{dirId}")
    public CommonResponseDto<?> createFile(
            @PathVariable(name = "dirId") Long dirId,
            @RequestPart(value = "name") String fileName,
            @RequestPart(value = "file")MultipartFile file
    ) {
        return CommonResponseDto.ok(createFileUseCase.execute(dirId, fileName, file));
    }

    @PatchMapping("/{documentId}")
    public CommonResponseDto<?> updateFile(
            @PathVariable(name = "documentId") Long documentId,
            @RequestBody UpdateFileRequestDto updateFileRequestDto
    ) {
        return CommonResponseDto.ok(updateFileUseCase.execute(documentId, updateFileRequestDto));
    }

    @DeleteMapping("/{documentId}")
    public CommonResponseDto<?> deleteFile(
            @PathVariable(name = "documentId") Long documentId
    ) {
        return CommonResponseDto.ok(deleteFileUseCase.execute(documentId));
    }
}
