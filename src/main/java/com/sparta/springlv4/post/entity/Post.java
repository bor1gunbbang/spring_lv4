package com.sparta.springlv4.post.entity;

import com.sparta.springlv4.post.dto.PostRequestDto;
import com.sparta.springlv4.post.dto.PostResponseDto;
import com.sparta.springlv4.user.entity.User;
import jakarta.persistence.*;
import jdk.jfr.Frequency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.web.JsonPath;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Setter
@DynamicInsert
@NoArgsConstructor
@Table(name = "post" )
public class Post {

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
    private User user;

    public Post(PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.user = user;
    }
}
