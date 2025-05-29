package com.cecd.help.document.application.dto.directory;

import java.util.List;
import lombok.Builder;

@Builder
public record ReadDirectoryListResponseDto(
        List<ReadDirectoryResponseDto> directoryList
){
}
