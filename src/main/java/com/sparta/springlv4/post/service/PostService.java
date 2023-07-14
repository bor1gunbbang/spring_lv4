package com.sparta.springlv4.post.service;

import com.sparta.springlv4.comment.entity.CommentEntity;
import com.sparta.springlv4.comment.repository.CommentRepository;
import com.sparta.springlv4.common.dto.ApiResponseDto;
import com.sparta.springlv4.common.security.UserDetailsImpl;
import com.sparta.springlv4.post.dto.PostRequestDto;
import com.sparta.springlv4.post.dto.PostResponseDto;
import com.sparta.springlv4.post.entity.PostEntity;
import com.sparta.springlv4.post.repository.PostRepository;
import com.sparta.springlv4.user.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    //-------게시글 작성
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, UserEntity username) {
        PostEntity post = new PostEntity(requestDto,username);

        postRepository.save(post);

        return new PostResponseDto(post);
    }
    //-------게시글 작성

    //전체게시글 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map((PostEntity post) ->
                new PostResponseDto(post, findCommentList(post.getId()))
        ).toList();
    }  //전체게시글 조회

    private List<CommentEntity> findCommentList(Long postId) {
        return commentRepository.findAllByPostIdOrderByCreatedAtDesc(postId);
    }
    //선택게시글 조회하기
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id){
        PostEntity post = findPost(id);
        List<CommentEntity> commentList = findCommentList(post.getId());

        return new PostResponseDto(post, commentList);
    }
    //선택게시글 조회하기
    private PostEntity findPost(Long id) {
        return postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("게시글을 찾지못했습니다."));
    }

    //선택게시글 삭제하기
    public void deletePost(Long id, UserEntity user) {
        PostEntity post = findPost(id);

        if (matchUser(post,user)){
            postRepository.delete(post);
        }else {
            throw new RuntimeException("UNAUTHORIZED_REQUEST");
        }
    }

    private boolean matchUser(PostEntity post, UserEntity user) {
        return post.getUser().getUsername().equals(user.getUsername());
    }
}
