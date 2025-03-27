package com.cecd.help.workspace.application.dto.member;

import java.util.List;
import lombok.Builder;

@Builder
public record ReadMemberListResponseDto(
        List<ReadMemberResponseDto> memberList
) {
}
