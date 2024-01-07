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

import com.edu.board.domain.Post;
import com.edu.board.service.PostService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/post")
public class PostController {
    
    private final PostService postService;

    PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.save(post);
    } // 요청 처리한 user가 body에 존재하므로 body에 있는 것을 가져와 저장해준다는 의미

    @GetMapping
    public List<Post> readAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}") // http:localhost:8080/users/hong
    public Post readOne(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post) {
        Post p = postService.findById(id);
        if (p != null) {
            p.setTitle(post.getTitle());
            return postService.save(p);
        }
        throw new EntityNotFoundException("게시물을 찾을 수 없습니다.");
    } 

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.deleteById(id);
    }
    
}
