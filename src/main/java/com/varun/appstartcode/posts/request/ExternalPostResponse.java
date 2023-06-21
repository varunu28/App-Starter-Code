package com.varun.appstartcode.posts.request;

import java.util.UUID;

public record ExternalPostResponse(UUID postId, String title, String content) {
}
