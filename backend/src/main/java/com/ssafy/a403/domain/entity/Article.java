package com.ssafy.a403.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_no")
    private int articleNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

//    @Enumerated(EnumType.STRING)
    private String category;

    @Column(name="article_title")
    private String articleTitle;

    @Column(name="article_content")
    private String articleContent;

    @Column(name="article_date")
    private LocalDateTime articleDate;

    @Builder
    public Article(User user, String category, String articleTitle, String articleContent, LocalDateTime articleDate){
        this.user = user;
        this.category = category;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleDate = articleDate;
    }

}
