package com.edu.board.Service;

import java.util.List;

import com.edu.board.Domain.Comment;
import com.edu.board.Repository.CommentRepository;

public class CommnetService {
    private final CommentRepository commentRepository;

    CommnetService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment) {
        if(comment.getContent().length() < 10) throw new RuntimeException("댓글은 10글자 이상이어야 합니다.");
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
