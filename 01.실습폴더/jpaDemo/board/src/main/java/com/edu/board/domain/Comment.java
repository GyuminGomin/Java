package com.edu.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore // Json으로 Parsing할 때, 해당 테이블의 데이터는 무시하라(예로 들자면, Comment를 참조할 때, Post를 볼 필요가 없다.)
    private Post post;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
}
