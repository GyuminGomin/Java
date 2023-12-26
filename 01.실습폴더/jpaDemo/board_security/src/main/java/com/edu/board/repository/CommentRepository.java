package com.edu.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.board.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
