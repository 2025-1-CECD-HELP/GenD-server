package com.cecd.help.workspace.domain.repository;

import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.util.List;

public interface WorkspaceRepository {

    void save(Workspace workspace);

    Workspace findById(Long id);

    void delete(Workspace workspace);

}
