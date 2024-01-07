package com.edu.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.board.domain.User;

public interface UserRepository extends JpaRepository<User, String>{

}
