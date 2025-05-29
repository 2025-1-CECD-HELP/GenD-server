package com.cecd.help.post.infrastructure.implement;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.post.domain.entity.Post;
import com.cecd.help.post.domain.repository.PostRepository;
import com.cecd.help.post.infrastructure.jpa.PostJpaRepository;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final PostJpaRepository postJpaRepository;

    @Override
    public void save(Post post) {
        postJpaRepository.save(post);
    }

    @Override
    public Post findById(Long postId) {
        return postJpaRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public void delete(Post post) {
        postJpaRepository.delete(post);
    }

    @Override
    public List<Post> findByWorkspace(Workspace workspace) {
        return postJpaRepository.findByWorkspace(workspace);
    }
}
