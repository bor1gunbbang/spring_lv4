package com.sparta.springlv4.post.service;

import com.sparta.springlv4.post.dto.PostRequestDto;
import com.sparta.springlv4.post.dto.PostResponseDto;
import com.sparta.springlv4.post.entity.Post;
import com.sparta.springlv4.post.repository.PostRepository;
import com.sparta.springlv4.user.entity.User;
import org.springframework.transaction.annotation.Transactional;

public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //-------게시글 작성
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, User username) {
        Post post = new Post(requestDto,username);

        postRepository.save(post);

        return new PostResponseDto(post);
    }
    //-------게시글 작성
}
