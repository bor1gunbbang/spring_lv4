package com.sparta.springlv4.post.dto;

import com.sparta.springlv4.post.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String contests;
    private String userName;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contests = post.getContents();
        this.userName = post.getUser().getUsername();
    }
}
