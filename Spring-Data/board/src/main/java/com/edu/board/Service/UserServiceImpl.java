package com.edu.board.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.board.Domain.User;
import com.edu.board.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user); // insert or update
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
    
}
