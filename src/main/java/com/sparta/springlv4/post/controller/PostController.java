package com.sparta.springlv4.post.controller;

import com.sparta.springlv4.common.dto.ApiResponseDto;
import com.sparta.springlv4.common.security.UserDetailsImpl;
import com.sparta.springlv4.post.dto.PostRequestDto;
import com.sparta.springlv4.post.dto.PostResponseDto;
import com.sparta.springlv4.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return postService.createPost(requestDto, userDetails.getUser());
    }
    //-------------게시글 작성

    //----게시글 날짜 기준으로 조회하기
    @GetMapping("/post")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }
//선택한 게시글 조회하기
    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id){
        try{
            return ResponseEntity.ok().body(postService.getPost(id));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(400L,e.getMessage()));
        }
    }
//선택한 게시글 조회하기
//선택게시글 삭제하기
@DeleteMapping("/post/{id}")
public ResponseEntity<ApiResponseDto> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
    try{
        postService.deletePost(id, userDetails.getUser());
    }catch (IllegalArgumentException e){
        return ResponseEntity.badRequest().body(new ApiResponseDto(400L,e.getMessage()));
    }

    return ResponseEntity.ok().body(new ApiResponseDto(200L,"게시글이 삭제되었습니다."));
}//선택한 게시글 삭제하기

}
