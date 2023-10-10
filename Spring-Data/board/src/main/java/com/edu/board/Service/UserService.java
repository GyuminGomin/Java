package com.edu.board.Service;

import java.util.List;

import com.edu.board.Domain.User;

public interface UserService {
    User save(User user);
    List<User> findAll();
    User findById(String id);
    void deleteById(String id);
}
