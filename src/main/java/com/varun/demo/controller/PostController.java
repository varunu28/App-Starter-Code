package com.varun.demo.controller;

import com.varun.demo.model.Post;
import com.varun.demo.repository.PostRepository;
import com.varun.demo.request.CreatePostRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository repository;

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Iterable<Post>> findAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody CreatePostRequest request) {
        repository.create(request.title(), request.content());
        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
    }
}
