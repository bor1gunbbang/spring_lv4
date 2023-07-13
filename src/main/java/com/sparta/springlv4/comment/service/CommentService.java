package com.sparta.springlv4.comment.service;

import com.sparta.springlv4.comment.dto.CommentRequestDto;
import com.sparta.springlv4.comment.dto.CommentResponseDto;
import com.sparta.springlv4.comment.entity.CommentEntity;
import com.sparta.springlv4.comment.repository.CommentRepository;
import com.sparta.springlv4.post.entity.PostEntity;
import com.sparta.springlv4.post.repository.PostRepository;
import com.sparta.springlv4.user.entity.UserEntity;
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

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, UserEntity user) {
        //선택한 게시글의 DB존재 여부 확인
        PostEntity post = findPost(requestDto.getPostId());

        //댓글 등록 후 등록 된 댓글 반환하기
        CommentEntity comment = new CommentEntity(requestDto, user,post);

        post.addCommentList(comment);

        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional(readOnly = true)
    public PostEntity findPost(Long id) {
        return postRepository.findById(id).orElseThrow(()->
                new RuntimeException("해당 게시글을 찾지 못했습니다."));
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto responseDto, UserEntity user) {
        CommentEntity comment = findComment(commentId);

        if (matchUser(comment, user)){
            comment.update(responseDto);

            return new CommentResponseDto(comment);
        }else {
            throw new RuntimeException("UNAUTHORIZED_REQUEST");
        }
    }

    private boolean matchUser(CommentEntity comment, UserEntity user) {
        return comment.getUser().getUsername().equals(user.getUsername());
    }

    @Transactional(readOnly = true)
    public CommentEntity findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(()->
                new RuntimeException("해당댓글을 찾지 못했습니다")
        );
    }
}
