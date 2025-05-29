package com.cecd.help.document.application.dto.file;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record ReadFileResponseDto(
        Long documentId,
        String documentTitle,
        String createAt,
        String documentFile
) {
}
