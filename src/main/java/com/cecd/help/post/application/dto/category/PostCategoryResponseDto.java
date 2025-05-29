package com.cecd.help.post.application.dto.category;

import lombok.Builder;

@Builder
public record PostCategoryResponseDto(
        Long categoryId,
        String categoryName
) {
}
