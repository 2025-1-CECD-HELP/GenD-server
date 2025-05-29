package com.cecd.help.document.infrastructure.impl;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.document.domain.repository.DirectoryRepository;
import com.cecd.help.document.infrastructure.jpa.DirectoryJpaRepository;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DirectoryRepositoryImpl implements DirectoryRepository {
    private final DirectoryJpaRepository directoryJpaRepository;

    @Override
    public void save(Directory directory) {
        directoryJpaRepository.save(directory);
    }

    @Override
    public Directory findById(Long directoryId) {
        return directoryJpaRepository.findById(directoryId)
                .orElseThrow(() -> new CustomException(ErrorCode.FAILURE_LOGIN)); // 디렉토리 가져오기 싫패
    }

    @Override
    public List<Directory> findByParIdAndWorkspace(Long parId, Workspace workspace) {
        return directoryJpaRepository.findByParIdAndWorkspace(parId, workspace);
    }

    @Override
    public List<Directory> findByWorkspace(Workspace workspace) {
        return directoryJpaRepository.findByWorkspace(workspace);
    }

    @Override
    public void deleteAll(List<Directory> directories) {
        directoryJpaRepository.deleteAll(directories);
    }

    @Override
    public Directory findByIdAndWorkspace(Long directoryId, Workspace workspace) {
        return directoryJpaRepository.findByIdAndWorkspace(directoryId, workspace)
                .orElseThrow(() -> new CustomException(ErrorCode.FAILURE_LOGIN)); // 디렉토리 가져오기 싫패
    }

    @Override
    public Directory findByWorkspaceAndParId(Workspace workspace, Long parId) {
        return directoryJpaRepository.findByWorkspaceAndParId(workspace, parId);
    }
}
