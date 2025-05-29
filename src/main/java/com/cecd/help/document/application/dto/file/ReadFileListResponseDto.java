package com.cecd.help.document.application.dto.file;

import java.util.List;
import lombok.Builder;

@Builder
public record ReadFileListResponseDto(
        Long dirId,
        String dirName,
        List<ReadDirectoryResponseDto> directoryList,
        List<ReadFileResponseDto> fileList

) {
}
