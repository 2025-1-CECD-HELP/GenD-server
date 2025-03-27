package com.cecd.help.workspace.application.dto.member;

import com.cecd.help.workspace.domain.type.WorkspaceRole;
import lombok.Builder;

@Builder
public record ReadMemberResponseDto(
        Long memberId,
        WorkspaceRole memberRole,
        String memberName,
        String memberImage
) {
}
