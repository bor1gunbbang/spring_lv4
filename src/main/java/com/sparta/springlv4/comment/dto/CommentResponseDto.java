package com.sparta.springlv4.comment.dto;

import com.sparta.springlv4.comment.entity.CommentEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long commentId;
    private String userId;
    private String content;
    private Integer like;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(CommentEntity comment) {
        this.commentId = comment.getId();
        this.userId = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.like = comment.getLikes();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
