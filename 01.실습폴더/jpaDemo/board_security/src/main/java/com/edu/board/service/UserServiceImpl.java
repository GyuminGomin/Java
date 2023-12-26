package com.edu.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.board.domain.User;
import com.edu.board.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository; // UserServiceImpl을 생성해주면 바로 할당해주는 것

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
        // User가 없는 경우도 있으므로 그렇게 하지 않도록 .orElse(null);을 추가 (그렇지 않으면 null을 출력)
        // .orElseThrow() -> 예외를 처리
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
    
}
