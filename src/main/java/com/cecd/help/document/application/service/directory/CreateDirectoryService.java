package com.cecd.help.document.application.service.directory;

import com.cecd.help.document.application.usecase.directory.CreateDirectoryUseCase;
import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.document.domain.repository.DirectoryRepository;
import com.cecd.help.document.presentation.request.DirectoryRequestDto;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateDirectoryService implements CreateDirectoryUseCase {
    private final WorkspaceRepository workspaceRepository;
    private final DirectoryRepository directoryRepository;

    @Override
    public Boolean execute(DirectoryRequestDto createDirectoryRequestDto, Long workspaceId, Long parentId) {
        Workspace workspace = workspaceRepository.findById(workspaceId);

        Directory newDirectory = Directory.builder()
                .parId(parentId)
                .dirName(createDirectoryRequestDto.directoryName())
                .workspace(workspace)
                .build();

        directoryRepository.save(newDirectory);
        return true;
    }
}
