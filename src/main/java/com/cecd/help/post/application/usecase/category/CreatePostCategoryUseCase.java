package com.cecd.help.post.application.usecase.category;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.post.presentation.request.CreatePostCategoryRequestDto;

@UseCase
public interface CreatePostCategoryUseCase {
    Boolean execute(CreatePostCategoryRequestDto createCategoryRequestDto, Long workspaceId);
}
