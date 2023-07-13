package com.sparta.springlv4.like.postlike.service;


import com.sparta.springlv4.common.dto.ApiResponseDto;
import com.sparta.springlv4.common.security.UserDetailsImpl;
import com.sparta.springlv4.like.postlike.controller.PostLikeController;
import com.sparta.springlv4.like.postlike.entity.PostLikeEntity;
import com.sparta.springlv4.like.postlike.repository.PostLikeRepository;
import com.sparta.springlv4.post.entity.PostEntity;
import com.sparta.springlv4.post.repository.PostRepository;
import com.sparta.springlv4.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    @Transactional
    public ResponseEntity<ApiResponseDto> insert(Long id , UserDetailsImpl userDetails){
        UserEntity user = userDetails.getUser();
        PostEntity post = findPostById(id);

        //본인 게시글에는 좋아요 불가

        try{
            if (postLikeRepository.findByUserEntityAndPostEntity(user,post).isPresent()){
                throw new Exception("\"Like\" already exists.");
            }else if (post.getUser().getUsername().equals(user.getUsername())){
                throw new Exception("Same person cannot \"Like\"");
            }else {
                post.countLike(); // post의 likes가 1 증가
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponseDto(400L, e.getMessage()));
        }

        PostLikeEntity like = new PostLikeEntity(user,post);

        postLikeRepository.save(like);
        return ResponseEntity.ok().body(new ApiResponseDto(200L,"게시글 좋아요 성공"));
    }

    private PostEntity findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(()->new RuntimeException("id 를 찾을 수 없습니다 : " +id));
    }

    @Transactional
    public ResponseEntity<ApiResponseDto> delete(Long id, UserDetailsImpl userDetails) {
        UserEntity user = userDetails.getUser();
        PostEntity post = findPostById(id);

        try{
            PostLikeEntity like = postLikeRepository.findByUserEntityAndPostEntity(user, post)
                    .orElseThrow(() -> new RuntimeException("\"Like\" not exists."));
            post.discountLike();
            postLikeRepository.delete(like);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponseDto(400L,e.getMessage()));
        }

        return ResponseEntity.ok().body(new ApiResponseDto(200L,"좋아요 삭제 성공"));
    }
}

