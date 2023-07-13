package com.sparta.springlv4.post.dto;

import com.sparta.springlv4.comment.dto.CommentResponseDto;
import com.sparta.springlv4.comment.entity.CommentEntity;
import com.sparta.springlv4.post.entity.PostEntity;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String userName;
    private Integer like;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList;

    public PostResponseDto(PostEntity post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.userName = post.getUser().getUsername();
    }
    public PostResponseDto(PostEntity post, List<CommentEntity> commentList) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.userName = post.getUser().getUsername();
        this.like = post.getLikes();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = commentList.stream().map(CommentResponseDto::new).toList();
    }
}
