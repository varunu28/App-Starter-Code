package com.varun.demo.controller;

import com.varun.demo.model.Post;
import com.varun.demo.repository.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository repository;

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Post> findAllPosts() {
        return repository.findAll();
    }
}
