package com.sparta.springlv4.comment.controller;


import com.sparta.springlv4.comment.dto.CommentRequestDto;
import com.sparta.springlv4.comment.dto.CommentResponseDto;
import com.sparta.springlv4.comment.service.CommentService;
import com.sparta.springlv4.common.dto.ApiResponseDto;
import com.sparta.springlv4.common.security.UserDetailsImpl;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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


    //댓글 생성하기
    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try{
            return ResponseEntity.ok().body(commentService.createComment(requestDto, userDetails.getUser()));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new ApiResponseDto(400L,e.getMessage()));
        }
    }
    //댓글 생성하기

    //댓글 수정하기
@PutMapping("/comment/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try{
            return ResponseEntity.ok().body(commentService.updateComment(id, requestDto,userDetails.getUser()));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new ApiResponseDto(400L,e.getMessage()));
        }
}
    //댓글 수정하기

    //댓글 삭제하기
    @DeleteMapping("/comment/{id}")
    public ApiResponseDto deleteComment(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try{
            commentService.deleteComment(id,userDetails.getUser());
        }catch (RuntimeException e){
            return new ApiResponseDto(400L,e.getMessage());
        }

        return new ApiResponseDto(200L,"댓글 삭제 성공");
    }
    //댓글 삭제하기
}
