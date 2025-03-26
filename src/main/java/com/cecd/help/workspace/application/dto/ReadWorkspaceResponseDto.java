package com.cecd.help.workspace.application.dto;

import com.cecd.help.workspace.domain.type.WorkspaceRole;
import lombok.Builder;

@Builder
public record ReadWorkspaceResponseDto(
        String workspaceName,
        String workspaceDescription,
        String imageUrl,
        WorkspaceRole workspaceRole
) {
}
