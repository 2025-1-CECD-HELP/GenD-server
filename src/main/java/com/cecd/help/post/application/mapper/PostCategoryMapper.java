package com.cecd.help.post.application.mapper;

import com.cecd.help.post.domain.entity.PostCategory;
import com.cecd.help.workspace.domain.entity.Workspace;
import org.springframework.stereotype.Service;

@Service
public class PostCategoryMapper {

    public PostCategory toEntity(String categoryName, Workspace workspace) {
        return PostCategory.builder()
                .categoryName(categoryName)
                .workspace(workspace)
                .build();
    }
}
