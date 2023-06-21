package com.varun.appstartcode.posts.request;

import jakarta.validation.constraints.NotNull;

public record CreatePostRequest(@NotNull String title, @NotNull String content) {}
