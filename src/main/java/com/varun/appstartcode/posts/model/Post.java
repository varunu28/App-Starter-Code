package com.varun.appstartcode.posts.model;


import com.varun.appstartcode.posts.request.ExternalPostResponse;

import java.util.UUID;

public record Post(Integer id, UUID externalId, String title, String content) {

    public ExternalPostResponse convertToExternalPostResponse() {
        return new ExternalPostResponse(externalId, title, content);
    }
}