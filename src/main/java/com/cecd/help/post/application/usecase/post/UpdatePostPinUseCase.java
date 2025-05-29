package com.cecd.help.post.application.usecase.post;

import com.cecd.help.core.annotation.UseCase;

@UseCase
public interface UpdatePostPinUseCase {
    Boolean execute(Long postId);
}
