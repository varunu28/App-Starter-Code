package com.varun.demo.posts.request;

import jakarta.validation.constraints.NotNull;

public record CreatePostRequest(@NotNull String title, @NotNull String content) {}
