package com.cecd.help.document.application.dto.directory;

import lombok.Builder;

@Builder
public record ReadDirectoryResponseDto(
        Long dirId,
        String dirName,
        Long parId

) {
}
