package com.cecd.help.workspace.domain.repository;

import com.cecd.help.workspace.domain.entity.Workspace;

public interface WorkspaceRepository {

    void save(Workspace workspace);

    Workspace findById(Long id);
}
