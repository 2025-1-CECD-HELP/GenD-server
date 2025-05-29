package com.cecd.help.post.application.service.post;

import com.cecd.help.core.util.S3Util;
import com.cecd.help.post.application.mapper.PostMapper;
import com.cecd.help.post.application.usecase.post.UpdatePostUseCase;
import com.cecd.help.post.domain.entity.PostCategory;
import com.cecd.help.post.domain.entity.Post;
import com.cecd.help.post.domain.repository.PostCategoryRepository;
import com.cecd.help.post.domain.repository.PostRepository;
import com.cecd.help.post.presentation.request.CreatePostRequestDto;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdatePostService implements UpdatePostUseCase {
    private final S3Util s3Util;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final WorkspaceRepository workspaceRepository;
    private final PostRepository postRepository;
    private final PostCategoryRepository categoryRepository;


    @Override
    public Boolean execute(Long postId, CreatePostRequestDto createPostRequestDto, MultipartFile multipartFile) {
        Post post = postRepository.findById(postId);
        PostCategory category = categoryRepository.findByCategoryName(createPostRequestDto.postCategory());

        if(!multipartFile.isEmpty()) {
            String url = s3Util.upload(multipartFile);
            post.updatePostImage(
                    createPostRequestDto.postTitle(),
                    createPostRequestDto.postDescription(),
                    category,
                    url
            );
        } else {
            post.updatePost(
                    createPostRequestDto.postTitle(),
                    createPostRequestDto.postDescription(),
                    category
            );
        }

        return true;
    }
}
