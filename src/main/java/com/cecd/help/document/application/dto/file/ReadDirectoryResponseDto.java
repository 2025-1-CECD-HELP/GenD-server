package com.cecd.help.document.application.dto.file;

import lombok.Builder;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
@Builder
public record ReadDirectoryResponseDto(
        Long dirId,
        String dirName,
        Long parId
) {
}
