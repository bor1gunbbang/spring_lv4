package com.sparta.springlv4.comment.service;

import com.sparta.springlv4.comment.dto.CommentResponseDto;
import com.sparta.springlv4.comment.repository.CommentRepository;
import com.sparta.springlv4.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }


    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComments(Long id) {
        return commentRepository.findAllByPost_Id(id).stream().map(CommentResponseDto::new).toList();
    }
}
