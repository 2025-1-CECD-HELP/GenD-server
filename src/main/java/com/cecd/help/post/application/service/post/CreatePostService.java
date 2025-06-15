package com.cecd.help.post.application.service.post;

import com.cecd.help.core.util.FcmUtil;
import com.cecd.help.core.util.S3Util;
import com.cecd.help.post.application.mapper.PostMapper;
import com.cecd.help.post.application.usecase.post.CreatePostUseCase;
import com.cecd.help.post.domain.entity.Post;
import com.cecd.help.post.domain.entity.PostCategory;
import com.cecd.help.post.domain.repository.PostCategoryRepository;
import com.cecd.help.post.domain.repository.PostRepository;
import com.cecd.help.post.presentation.request.CreatePostRequestDto;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class CreatePostService implements CreatePostUseCase {
    private final PostRepository postRepository;
    private final S3Util s3Util;
    private final WorkspaceRepository workspaceRepository;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;
    private final PostCategoryRepository postCategoryRepository;
    private final FcmUtil fcmUtil;

    @Override
    public Boolean execute(CreatePostRequestDto createPostRequestDto, MultipartFile multipartFile, UUID userId, Long workspaceId) {
        User user = userRepository.findById(userId);
        String url = s3Util.upload(multipartFile);

        Workspace workspace = workspaceRepository.findById(workspaceId);

        Member member = memberRepository.findByUserAndWorkspace(user, workspace);

        PostCategory postCategory = postCategoryRepository.findByCategoryNameAndWorkspace(createPostRequestDto.postCategory(), workspace);
        System.out.print(postCategory.getCategoryName());
        Post newPost = postMapper.newPost(
                createPostRequestDto.postTitle(),
                createPostRequestDto.postDescription(),
                url,
                member,
                workspace,
                postCategory
        );

        postRepository.save(newPost);

        List<Member> members = memberRepository.findAllByWorkspaceAndIsSchedule(workspace, true);

        members.forEach(sendmember -> fcmUtil.sendMessage(
                "게시글 알림",
                workspace.getWorkspaceName() + "워크스페이스에서 게시글이 올라왔습니다.",
                sendmember.getUser().getFcmToken(),
                workspace.getId()
                ));

        return true;
    }
}
