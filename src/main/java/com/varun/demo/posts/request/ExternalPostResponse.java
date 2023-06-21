package com.varun.demo.posts.request;

import java.util.UUID;

public record ExternalPostResponse(UUID postId, String title, String content) {
}
