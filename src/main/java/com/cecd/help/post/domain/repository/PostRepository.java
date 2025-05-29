package com.cecd.help.post.domain.repository;

import com.cecd.help.post.domain.entity.Post;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.util.List;
import java.util.WeakHashMap;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository {
    void save(Post post);

    Post findById(Long postId);

    void delete(Post post);

    List<Post> findByWorkspace(Workspace workspace);
}
