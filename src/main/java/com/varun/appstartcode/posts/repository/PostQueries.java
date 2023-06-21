package com.varun.appstartcode.posts.repository;

public class PostQueries {

    public static final String CREATE_POST_QUERY = "INSERT INTO posts(external_id, title, content) VALUES (?, ?, ?)";

    public static final String GET_ALL_POSTS_QUERY = "SELECT id, external_id, title, content FROM posts";

    public static final String GET_POST_BY_ID = "SELECT id, external_id, title, content FROM posts where external_id = ?";

    private PostQueries() {}
}
