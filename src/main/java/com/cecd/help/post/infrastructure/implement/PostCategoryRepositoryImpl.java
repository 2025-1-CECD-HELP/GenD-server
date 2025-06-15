package com.cecd.help.post.infrastructure.implement;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.post.domain.entity.PostCategory;
import com.cecd.help.post.domain.repository.PostCategoryRepository;
import com.cecd.help.post.infrastructure.jpa.PostCategoryJpaRepository;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostCategoryRepositoryImpl implements PostCategoryRepository {
    private final PostCategoryJpaRepository postCategoryJpaRepository;

    @Override
    public PostCategory findByCategoryNameAndWorkspace(String categoryName, Workspace workspace) {
        return postCategoryJpaRepository.findByCategoryNameAndWorkspace(categoryName, workspace)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER)); // 카테고리 없음
    }

    @Override
    public Boolean existsByCategoryNameAndWorkspace(String categoryName, Workspace workspace) {
        return postCategoryJpaRepository.existsByCategoryNameAndWorkspace(categoryName, workspace);
    }

    @Override
    public void save(PostCategory category) {
        postCategoryJpaRepository.save(category);
    }

    @Override
    public List<PostCategory> findByWorkspace(Workspace workspace) {
        return postCategoryJpaRepository.findByWorkspace(workspace);
    }
}
