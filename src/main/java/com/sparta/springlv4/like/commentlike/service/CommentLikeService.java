package com.sparta.springlv4.like.commentlike.service;

import com.sparta.springlv4.comment.entity.CommentEntity;
import com.sparta.springlv4.comment.repository.CommentRepository;
import com.sparta.springlv4.common.dto.ApiResponseDto;
import com.sparta.springlv4.common.security.UserDetailsImpl;
import com.sparta.springlv4.like.commentlike.entity.CommentLikeEntity;
import com.sparta.springlv4.like.commentlike.repository.CommentLikeRepository;
import com.sparta.springlv4.user.entity.UserEntity;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.stream.events.Comment;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    @Transactional
    public ResponseEntity<ApiResponseDto> insert(Long id, UserDetailsImpl userDetails) {
        UserEntity user = userDetails.getUser();
        CommentEntity comment = findCommentById(id);

        try{
            if (commentLikeRepository.findByUserEntityAndCommentEntity(user, comment).isPresent()) {
                throw new Exception("이미 좋아요를 누르셨습니다.");

            }else if (comment.getUser().getUsername().equals(userDetails.getUsername())) {
                throw new Exception("본인이 작성한글은 좋아요를 누를수 없습니다.");
            }else {
                comment.countLike();
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponseDto(400L,e.getMessage()));
        }

        CommentLikeEntity like = new CommentLikeEntity(user,comment);
        commentLikeRepository.save(like);
        return ResponseEntity.ok().body(new ApiResponseDto(200L,"좋아요 누르기 성공"));

    }

    private CommentEntity findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("id를 찾을수 없습니다 : " + id));
    }

    public ResponseEntity<ApiResponseDto> delete(Long id, UserDetailsImpl userDetails) {
        UserEntity user = userDetails.getUser();
        CommentEntity comment = findCommentById(id);

        try {
            CommentLikeEntity like = commentLikeRepository.findByUserEntityAndCommentEntity(user, comment)
                    .orElseThrow(()-> new RuntimeException("좋아요가 없습니다."));

            comment.discountLike();
            commentLikeRepository.delete(like);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponseDto(400L, e.getMessage()));
        }

        return ResponseEntity.ok().body(new ApiResponseDto(200L,"좋아요 삭제 성공"));    }
}
