package com.edu.board.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.board.Domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
