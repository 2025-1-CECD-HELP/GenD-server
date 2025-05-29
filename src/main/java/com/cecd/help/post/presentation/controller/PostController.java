package com.cecd.help.post.presentation.controller;

import com.cecd.help.core.annotation.UserId;
import com.cecd.help.core.common.CommonResponseDto;
import com.cecd.help.post.application.usecase.post.CreatePostUseCase;
import com.cecd.help.post.application.usecase.post.DeletePostUseCase;
import com.cecd.help.post.application.usecase.post.ReadPostListUseCase;
import com.cecd.help.post.application.usecase.post.UpdatePostPinUseCase;
import com.cecd.help.post.application.usecase.post.UpdatePostUseCase;
import com.cecd.help.post.presentation.request.CreatePostRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final ReadPostListUseCase readPostListUseCase;
    private final CreatePostUseCase createPostUseCase;
    private final UpdatePostUseCase updatePostUseCase;
    private final DeletePostUseCase deletePostUseCase;
    private final UpdatePostPinUseCase updatePostPinUseCase;

    @GetMapping("/{workspaceId}")
    public CommonResponseDto<?> readPostList(
            @PathVariable(name = "workspaceId") Long workspaceId
    ) {
        return CommonResponseDto.ok(readPostListUseCase.execute(workspaceId));
    }

    @PostMapping(value = "/{workspaceId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE} )
    public CommonResponseDto<?> createPost(
            @PathVariable(name = "workspaceId") Long workspaceId,
            @UserId UUID userId,
            @RequestPart(value = "posts") CreatePostRequestDto createPostRequestDto,
            @RequestPart(value = "image") MultipartFile file
    ) {
        return CommonResponseDto.ok(createPostUseCase.execute(createPostRequestDto, file, userId, workspaceId));
    }

    @PatchMapping(value = "/{postId}", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public CommonResponseDto<?> updatePost(
            @PathVariable(name = "postId") Long postId,
            @RequestPart(value = "posts") CreatePostRequestDto createPostRequestDto,
            @RequestPart(value = "image", required = false) MultipartFile file
    ) {
        return CommonResponseDto.ok(updatePostUseCase.execute(postId,createPostRequestDto, file));
    }

    @DeleteMapping("/{postId}")
    public CommonResponseDto<?> deletePost(
            @PathVariable(name = "postId") Long postId
    ) {
        return CommonResponseDto.ok(deletePostUseCase.execute(postId));
    }

    @PatchMapping("/{postId}/pin")
    public CommonResponseDto<?> updatePin(
            @PathVariable(name = "postId") Long postId
    ) {
        return CommonResponseDto.ok(updatePostPinUseCase.execute(postId));
    }

}
