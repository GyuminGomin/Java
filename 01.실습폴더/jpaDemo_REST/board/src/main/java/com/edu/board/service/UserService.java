package com.edu.board.service;

import java.util.List;

import com.edu.board.domain.User;

public interface UserService {
    User save(User user);
    List<User> findAll();
    User findById(String id);
    void deleteById(String id);
}
