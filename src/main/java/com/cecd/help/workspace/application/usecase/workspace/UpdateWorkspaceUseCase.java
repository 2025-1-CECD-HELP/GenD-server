package com.cecd.help.workspace.application.usecase.workspace;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.workspace.presentation.request.UpdateWorkspaceRequestDto;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

@UseCase
public interface UpdateWorkspaceUseCase {
    void execute(Long workspaceId, MultipartFile multipartFile, UpdateWorkspaceRequestDto updateWorkspaceRequestDto, UUID userId);
}
