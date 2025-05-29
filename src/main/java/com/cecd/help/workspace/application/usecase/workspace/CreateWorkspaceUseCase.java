package com.cecd.help.workspace.application.usecase.workspace;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.workspace.presentation.request.CreateWorkspaceRequestDto;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

@UseCase
public interface CreateWorkspaceUseCase {
    void execute(MultipartFile multipartFile, CreateWorkspaceRequestDto createWorkspaceRequestDto, UUID userId);
}
