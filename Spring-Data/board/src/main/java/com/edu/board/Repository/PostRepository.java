package com.edu.board.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.board.Domain.User;

public interface PostRepository extends JpaRepository<User, String>{
    
}
