package com.cecd.help.workspace.presentation.request;

public record UpdateWorkspaceRequestDto(
        String imageUrl,
        String workspaceName,
        String workspaceDescription
) {
}
