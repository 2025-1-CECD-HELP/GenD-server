package com.cecd.help.post.presentation.controller;

import com.cecd.help.core.common.CommonResponseDto;
import com.cecd.help.post.application.usecase.category.CreatePostCategoryUseCase;
import com.cecd.help.post.application.usecase.category.ReadPostCategoryListUseCase;
import com.cecd.help.post.presentation.request.CreatePostCategoryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostCategoryController {
    private final CreatePostCategoryUseCase createPostCategoryUseCase;
    private final ReadPostCategoryListUseCase readPostCategoryListUseCase;

    @PostMapping("/{workspaceId}/category")
    public CommonResponseDto<?> createCategory(
            @RequestBody CreatePostCategoryRequestDto createCategoryRequestDto,
            @PathVariable(name = "workspaceId") Long workspaceId
    ) {
        return CommonResponseDto.ok(createPostCategoryUseCase.execute(createCategoryRequestDto, workspaceId));
    }

    @GetMapping("{workspaceId}/category")
    public CommonResponseDto<?> readCategory(
            @PathVariable(name = "workspaceId") Long workspaceId
    ) {
        return CommonResponseDto.ok(readPostCategoryListUseCase.execute(workspaceId));
    }
}
