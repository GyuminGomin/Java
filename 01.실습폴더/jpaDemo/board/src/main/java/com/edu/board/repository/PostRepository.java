package com.edu.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.board.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    
}
