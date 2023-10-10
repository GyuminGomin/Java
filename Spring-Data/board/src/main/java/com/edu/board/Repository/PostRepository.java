package com.edu.board.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.board.Domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    
}
