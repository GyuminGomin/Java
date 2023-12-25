package com.edu.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.board.domain.User;
import com.edu.board.service.UserService;

import jakarta.persistence.EntityNotFoundException;

// @RestController
// @RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    } // 요청 처리한 user가 body에 존재하므로 body에 있는 것을 가져와 저장해준다는 의미

    @GetMapping
    public List<User> readAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}") // http:localhost:8080/users/hong
    public User readOne(@PathVariable String id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable String id, @RequestBody User user) {
        User u = userService.findById(id);
        if (u != null) {
            u.setUserName(user.getUserName());
            return userService.save(u);
        }
        throw new EntityNotFoundException("사용자를 찾을 수 없습니다.");
    } 

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userService.deleteById(id);
    }
    
}
