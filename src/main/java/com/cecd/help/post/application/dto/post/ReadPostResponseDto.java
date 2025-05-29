package com.cecd.help.post.application.dto.post;

import lombok.Builder;

@Builder
public record ReadPostResponseDto(
        Long postId,
        String postTitle,
        String postDescription,
        String postCategory,
        String postWriter,
        String postImageUrl,
        Boolean isPin
) {
}

/*
                "postId": 1,
                "postTitle": "제목",
                "postDescrition": "설명",
                "postImage": "이미지",
                "postWriter": "박민기",
                "isPin": true || false
                "postCategory": "공지사항" ENUM
 */