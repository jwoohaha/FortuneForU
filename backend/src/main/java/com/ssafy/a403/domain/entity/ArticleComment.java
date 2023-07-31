package com.ssafy.a403.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_no")
    private int commentNo;

    @ManyToOne
    @JoinColumn(name = "article_no")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    //댓글 내용
    @Column(name="comment_content")
    private String commentContent;

    @Column(name="comment_date")
    private LocalDateTime commentDate;


}
