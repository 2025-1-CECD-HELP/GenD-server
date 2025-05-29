package com.cecd.help.post.application.dto.category;

import java.util.List;
import lombok.Builder;

@Builder
public record PostCategoryListResponseDto(
        List<PostCategoryResponseDto> categoryList
) {
}
