package com.edu.board.Controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.board.Domain.User;
import com.edu.board.Service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping
    public List<User> readAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}") //http://localhost:8080/users/hong
    public User readOne(@PathVariable String id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable String id, @RequestBody User user) {
        User findUser = userService.findById(id);
        if (findUser != null) {
            findUser.setUserName(user.getUserName());
            return userService.save(findUser);
        }
        throw new EntityNotFoundException("사용자를 찾을 수 없습니다.");        
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userService.deleteById(id);
    }
}
