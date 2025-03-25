package com.cecd.help.workspace.infrastructure.implement;

import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import com.cecd.help.workspace.infrastructure.jpa.WorkspaceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WorkspaceRepositoryImpl implements WorkspaceRepository {
    private final WorkspaceJpaRepository workspaceJpaRepository;

    @Override
    public void save(Workspace workspace) {
        workspaceJpaRepository.save(workspace);
    }
}
