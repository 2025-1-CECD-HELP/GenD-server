package com.cecd.help.post.application.service.category;

import com.cecd.help.post.application.dto.category.PostCategoryListResponseDto;
import com.cecd.help.post.application.dto.category.PostCategoryResponseDto;
import com.cecd.help.post.application.usecase.category.ReadPostCategoryListUseCase;
import com.cecd.help.post.domain.entity.PostCategory;
import com.cecd.help.post.domain.repository.PostCategoryRepository;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadPostCategoryListService implements ReadPostCategoryListUseCase {
    private final WorkspaceRepository  workspaceRepository;
    private final PostCategoryRepository categoryRepository;

    @Override
    public PostCategoryListResponseDto execute(Long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId);

        List<PostCategory> categories  = categoryRepository.findByWorkspace(workspace);

        List<PostCategoryResponseDto> categoryList = categories.stream()
                .map(category -> PostCategoryResponseDto.builder()
                        .categoryId(category.getId())
                        .categoryName(category.getCategoryName())
                        .build())
                .collect(Collectors.toList());


        return PostCategoryListResponseDto.builder()
                .categoryList(categoryList)
                .build();
    }
}
