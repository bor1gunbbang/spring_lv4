package com.sparta.springlv4.post.entity;

import com.sparta.springlv4.comment.entity.CommentEntity;
import com.sparta.springlv4.common.entitiy.Timestamped;
import com.sparta.springlv4.post.dto.PostRequestDto;
import com.sparta.springlv4.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@Entity
@Getter
@Setter
@DynamicInsert
@NoArgsConstructor
@Table(name = "post" )
public class PostEntity extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_Id")
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "contents",nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "comment_list")
    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<CommentEntity> commentList;

    @ColumnDefault("0")
    @Column(name = "likes", nullable = false)
    private Integer likes;


    public void addCommentList(CommentEntity comment) {
        this.commentList.add(comment);
        comment.setPost(this);
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }
    public PostEntity(PostRequestDto requestDto, UserEntity user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.user = user;
    }

    public void countLike() {
        this.likes++;
    }

    public void discountLike() {
        this.likes--;
    }
}
