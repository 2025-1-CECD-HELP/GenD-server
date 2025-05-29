package com.cecd.help.post.application.usecase.post;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.post.presentation.request.CreatePostRequestDto;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

@UseCase
public interface CreatePostUseCase {
    Boolean execute(CreatePostRequestDto createPostRequestDto, MultipartFile multipartFile, UUID userId, Long workspaceId);
}
