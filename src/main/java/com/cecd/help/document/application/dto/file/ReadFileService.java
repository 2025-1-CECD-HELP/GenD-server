package com.cecd.help.document.application.dto.file;

import com.cecd.help.document.application.usecase.file.ReadFileUseCase;
import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.document.domain.entity.Store;
import com.cecd.help.document.domain.repository.DirectoryRepository;
import com.cecd.help.document.domain.repository.StoreRepository;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadFileService implements ReadFileUseCase {
    private final StoreRepository storeRepository;
    private final WorkspaceRepository workspaceRepository;
    private final DirectoryRepository directoryRepository;


    public ReadFileListResponseDto execute(Long workspaceId, Long dirId) {
        Workspace workspace = workspaceRepository.findById(workspaceId);

        Directory directory = directoryRepository.findByIdAndWorkspace(dirId, workspace);

        List<Store> stores = storeRepository.findByDirectory(directory);

        List<ReadFileResponseDto> fileResponseDtos = stores.stream().map(
                    store -> ReadFileResponseDto.builder()
                            .documentId(store.getId())
                            .documentTitle(store.getFileName())
                            .createAt(store.getCreateAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                            .documentFile(store.getFileUrl())
                            .build()
            ).toList();

        List<Directory> directories = directoryRepository.findByParIdAndWorkspace(dirId, workspace);

        List<ReadDirectoryResponseDto> directoryResponseDtos = directories.stream()
                .map(directory1 -> ReadDirectoryResponseDto.builder()
                        .dirName(directory1.getDirName())
                        .dirId(directory1.getId())
                        .parId(directory1.getParId())
                        .build())
                .toList();

        return ReadFileListResponseDto.builder()
                .dirId(dirId)
                .dirName(directory.getDirName())
                .directoryList(directoryResponseDtos)
                .fileList(fileResponseDtos)
                .build();

    }
}
