package com.cecd.help.document.application.service.directory;

import com.cecd.help.document.application.dto.directory.ReadDirectoryListResponseDto;
import com.cecd.help.document.application.dto.directory.ReadDirectoryResponseDto;
import com.cecd.help.document.application.usecase.directory.ReadDirectoryUseCase;
import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.document.domain.repository.DirectoryRepository;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadDirectoryListService implements ReadDirectoryUseCase {
    private final WorkspaceRepository workspaceRepository;
    private final DirectoryRepository directoryRepository;

     @Override
     public ReadDirectoryListResponseDto execute(Long workspaceId) {
         Workspace workspace = workspaceRepository.findById(workspaceId);

         List<Directory> directories = directoryRepository.findByWorkspace(workspace);

         List<ReadDirectoryResponseDto> responseDtos = directories.stream()
                 .map(directory -> ReadDirectoryResponseDto.builder()
                         .dirId(directory.getId())
                         .dirName(directory.getDirName())
                         .parId(directory.getParId())
                         .build())
                 .toList();

         return ReadDirectoryListResponseDto.builder()
                 .directoryList(responseDtos)
                 .build();
    }
}
