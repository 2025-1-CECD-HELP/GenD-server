package com.cecd.help.post.infrastructure.jpa;

import com.cecd.help.post.domain.entity.Post;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
    List<Post> findByWorkspace(Workspace workspace);
}
