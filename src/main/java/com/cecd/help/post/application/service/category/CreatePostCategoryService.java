package com.cecd.help.post.application.service.category;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.post.application.mapper.PostCategoryMapper;
import com.cecd.help.post.application.usecase.category.CreatePostCategoryUseCase;
import com.cecd.help.post.domain.entity.PostCategory;
import com.cecd.help.post.domain.repository.PostCategoryRepository;
import com.cecd.help.post.presentation.request.CreatePostCategoryRequestDto;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreatePostCategoryService implements CreatePostCategoryUseCase {
    private final PostCategoryRepository categoryRepository;
    private final WorkspaceRepository workspaceRepository;
    private final PostCategoryMapper categoryMapper;

    @Override
    public Boolean execute(CreatePostCategoryRequestDto createCategoryRequestDto, Long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId);

        if(categoryRepository.existsByCategoryNameAndWorkspace(createCategoryRequestDto.categoryName(), workspace))
            throw new CustomException(ErrorCode.NOT_FOUND_USER); // 이미 존재하는 카테고리입니다.

        PostCategory newCategory = categoryMapper.toEntity(createCategoryRequestDto.categoryName(), workspace);
        categoryRepository.save(newCategory);

        return true;
    }
}
