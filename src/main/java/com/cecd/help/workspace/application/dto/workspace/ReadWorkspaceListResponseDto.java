package com.cecd.help.workspace.application.dto.workspace;

import java.util.List;
import lombok.Builder;

@Builder
public record ReadWorkspaceListResponseDto(
        List<ReadWorkspaceResponseDto> workspaceList
) {
}
