package com.cecd.help.post.application.service.post;

import com.cecd.help.post.application.usecase.post.DeletePostUseCase;
import com.cecd.help.post.domain.entity.Post;
import com.cecd.help.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeletePostService implements DeletePostUseCase {
    private final PostRepository postRepository;

    @Override
    public Boolean execute(Long postId) {
        Post post = postRepository.findById(postId);
        postRepository.delete(post);
        return true;
    }

}
