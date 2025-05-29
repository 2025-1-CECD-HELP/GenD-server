package com.cecd.help.post.application.dto.post;

import java.util.List;
import lombok.Builder;

@Builder
public record ReadPostListResponseDto(
        List<ReadPostResponseDto> postList
) {
}
