package com.sparta.springlv4.post.controller;

import com.sparta.springlv4.common.security.UserDetailsImpl;
import com.sparta.springlv4.post.dto.PostRequestDto;
import com.sparta.springlv4.post.dto.PostResponseDto;
import com.sparta.springlv4.post.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //-------------게시글 작성
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.createPost(requestDto, userDetails.getUsername());
    }
    //-------------게시글 작성



}
