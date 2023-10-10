package com.edu.board.Service;

import java.util.List;

import com.edu.board.Domain.Post;
import com.edu.board.Repository.PostRepository;

public class PostService {

    private final PostRepository postRepository;

    PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findyById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
