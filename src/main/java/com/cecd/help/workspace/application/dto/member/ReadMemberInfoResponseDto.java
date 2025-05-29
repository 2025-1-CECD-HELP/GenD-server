package com.cecd.help.workspace.application.dto.member;

import com.cecd.help.user.domain.type.ELoginProvider;
import lombok.Builder;

@Builder
public record ReadMemberInfoResponseDto(
        Long memberId,
        String memberName,
        String loginProvider,
        String memberEmail
) {
}
