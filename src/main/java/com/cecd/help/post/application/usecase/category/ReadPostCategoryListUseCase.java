package com.cecd.help.post.application.usecase.category;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.post.application.dto.category.PostCategoryListResponseDto;

@UseCase
public interface ReadPostCategoryListUseCase {
    PostCategoryListResponseDto execute(Long workspaceId);
}
