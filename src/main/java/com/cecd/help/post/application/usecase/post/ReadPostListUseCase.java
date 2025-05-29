package com.cecd.help.post.application.usecase.post;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.post.application.dto.post.ReadPostListResponseDto;

@UseCase
public interface ReadPostListUseCase {
    ReadPostListResponseDto execute(Long workspaceId);
}
