package com.sparta.springlv4.comment.controller;


import com.sparta.springlv4.comment.dto.CommentResponseDto;
import com.sparta.springlv4.comment.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //댓글 가져오기
    @GetMapping("/comment")
    public List<CommentResponseDto> getComments(@RequestParam Long postId){
        return commentService.getComments(postId);
    }
    //댓글 가져오기
}
