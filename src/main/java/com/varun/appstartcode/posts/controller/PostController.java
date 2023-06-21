package com.varun.appstartcode.posts.controller;

import com.varun.appstartcode.posts.repository.PostRepository;
import com.varun.appstartcode.posts.request.CreatePostRequest;
import com.varun.appstartcode.posts.request.ExternalPostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository repository;

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Iterable<ExternalPostResponse>> findAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExternalPostResponse> findPostByid(@PathVariable("id")UUID id) {
        Optional<ExternalPostResponse> response = repository.findPostById(id);
        return response.map(externalPostResponse -> ResponseEntity.status(HttpStatus.OK).body(externalPostResponse))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping
    public ResponseEntity<ExternalPostResponse> createPost(@RequestBody CreatePostRequest request) {
        ExternalPostResponse postResponse = repository.create(request.title(), request.content());
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);
    }
}
