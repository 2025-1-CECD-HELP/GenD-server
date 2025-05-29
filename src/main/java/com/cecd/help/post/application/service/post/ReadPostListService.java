package com.cecd.help.post.application.service.post;

import com.cecd.help.post.application.dto.post.ReadPostListResponseDto;
import com.cecd.help.post.application.dto.post.ReadPostResponseDto;
import com.cecd.help.post.application.usecase.post.ReadPostListUseCase;
import com.cecd.help.post.domain.entity.Post;
import com.cecd.help.post.domain.repository.PostRepository;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadPostListService implements ReadPostListUseCase {
    private final WorkspaceRepository workspaceRepository;
    private final PostRepository postRepository;

    @Override
    public ReadPostListResponseDto execute(Long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId);

        List<Post> posts = postRepository.findByWorkspace(workspace);


        List<ReadPostResponseDto> readPost = posts.stream()
                .map(this::of)
                .toList();

        return ReadPostListResponseDto.builder()
                .postList(readPost)
                .build();
    }

    private ReadPostResponseDto of(Post post) {
        return ReadPostResponseDto.builder()
                .postId(post.getId())
                .postTitle(post.getPostTitle())
                .postDescription(post.getPostDescription())
                .postImageUrl(post.getPostImageUrl())
                .isPin(post.getIsPin())
                .postWriter(post.getMember().getUser().getNickname())
                .postCategory(post.getPostCategory().getCategoryName())
                .build();
    }
}
