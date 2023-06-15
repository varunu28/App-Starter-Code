package com.varun.demo.repository;

public class PostQueries {

    public static final String CREATE_POST_QUERY = "INSERT INTO posts(title, content) VALUES (?, ?)";

    public static final String GET_ALL_POSTS_QUERY = "SELECT id, title, content FROM posts";

    private PostQueries() {}
}
