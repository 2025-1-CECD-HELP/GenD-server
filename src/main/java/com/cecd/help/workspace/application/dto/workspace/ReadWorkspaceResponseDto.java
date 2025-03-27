package com.cecd.help.workspace.application.dto.workspace;

import com.cecd.help.workspace.domain.type.WorkspaceRole;
import lombok.Builder;

@Builder
public record ReadWorkspaceResponseDto(
        Long workspaceId,
        String workspaceName,
        String workspaceDescription,
        String imageUrl,
        WorkspaceRole workspaceRole
) {
}
