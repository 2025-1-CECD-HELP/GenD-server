package com.cecd.help.post.domain.repository;

import com.cecd.help.post.domain.entity.PostCategory;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCategoryRepository {
    PostCategory findByCategoryNameAndWorkspace(String categoryName, Workspace workspace);
    Boolean existsByCategoryNameAndWorkspace(String categoryName, Workspace workspace);
    void save(PostCategory category);

    List<PostCategory> findByWorkspace(Workspace workspace);
}
