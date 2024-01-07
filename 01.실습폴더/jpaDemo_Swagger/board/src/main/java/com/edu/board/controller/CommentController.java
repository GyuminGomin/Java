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

import com.edu.board.domain.Comment;
import com.edu.board.service.CommentService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/comment")
public class CommentController {
    
    private final CommentService commentService;

    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.save(comment);
    } // 요청 처리한 user가 body에 존재하므로 body에 있는 것을 가져와 저장해준다는 의미

    @GetMapping
    public List<Comment> readAll() {
        return commentService.findAll();
    }

    @GetMapping("/{id}") // http:localhost:8080/users/hong
    public Comment readOne(@PathVariable Long id) {
        return commentService.findById(id);
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable Long id, @RequestBody Comment comment) {
        Comment c = commentService.findById(id);
        if (c != null) {
            c.setContent(comment.getContent());
            return commentService.save(c);
        }
        throw new EntityNotFoundException("작성한 코멘트를 찾을 수 없습니다.");
    } 

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        commentService.deleteById(id);
    }
    
}
