package com.cecd.help.post.application.usecase.post;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.post.presentation.request.CreatePostRequestDto;
import org.springframework.web.multipart.MultipartFile;

@UseCase
public interface UpdatePostUseCase {
    Boolean execute(Long postId, CreatePostRequestDto createPostRequestDto, MultipartFile multipartFile);
}
