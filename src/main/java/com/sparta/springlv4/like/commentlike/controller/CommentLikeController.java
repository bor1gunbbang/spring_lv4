package com.sparta.springlv4.like.commentlike.controller;

import com.sparta.springlv4.common.dto.ApiResponseDto;
import com.sparta.springlv4.common.security.UserDetailsImpl;
import com.sparta.springlv4.like.commentlike.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    @PostMapping("/comment/{id}/like")
    public ResponseEntity<ApiResponseDto> insert(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails)throws Exception{
        return commentLikeService.insert(id,userDetails);

    }

    @DeleteMapping("/comment/{id}/like")
    public ResponseEntity<ApiResponseDto> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails)throws Exception{
        return commentLikeService.delete(id,userDetails);
    }

}
