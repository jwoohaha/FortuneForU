package com.ssafy.a403.domain.article.entity;

import com.ssafy.a403.domain.member.entity.Member;
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

<<<<<<< HEAD
    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;
=======
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;
>>>>>>> b4b7c0a706fd0fa2bc6672e322f6c95753693c66

//    @Enumerated(EnumType.STRING)
    private String category;

    @Column(name="article_title")
    private String articleTitle;

    @Column(name="article_content")
    private String articleContent;

    @Column(name="article_date")
    private LocalDateTime articleDate;

    @Builder
    public Article(Member member, String category, String articleTitle, String articleContent, LocalDateTime articleDate){
        this.member = member;
        this.category = category;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleDate = articleDate;
    }

}
