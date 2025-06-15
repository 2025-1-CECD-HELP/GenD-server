package com.cecd.help.post.infrastructure.jpa;

import com.cecd.help.post.domain.entity.PostCategory;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCategoryJpaRepository extends JpaRepository<PostCategory, Long> {
    Optional<PostCategory> findByCategoryNameAndWorkspace(String categoryName, Workspace workspace);
    Boolean existsByCategoryNameAndWorkspace(String categoryName, Workspace workspace);
    List<PostCategory> findByWorkspace(Workspace workspace);
}
