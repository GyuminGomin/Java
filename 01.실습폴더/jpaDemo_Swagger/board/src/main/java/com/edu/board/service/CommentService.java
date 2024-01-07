package com.edu.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.board.domain.Comment;
import com.edu.board.repository.CommentRepository;

@Service
public class CommentService {
    
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment) {
        if (comment.getContent().length() < 10) throw new RuntimeException("댓글은 10글자 이상이어야 합니다.");
        return commentRepository.save(comment);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
    
}
