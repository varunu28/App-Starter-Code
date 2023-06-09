package com.varun.appstartcode.posts.controller;

import com.varun.appstartcode.posts.request.CreatePostRequest;
import com.varun.appstartcode.posts.request.ExternalPostResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@Testcontainers
class PostControllerE2ETest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Container
    public static PostgreSQLContainer postgres = new PostgreSQLContainer<>("postgres:15.3")
            .withUsername("postgres")
            .withPassword("postgres")
            .withDatabaseName("postgres")
            .withFileSystemBind("./init-scripts", "/docker-entrypoint-initdb.d", BindMode.READ_ONLY);

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.username", postgres::getUsername);
    }

    @Test
    void postCreationSuccessful() {
        // Create a post
        Response createPostResponse = given()
                .header("Content-type", "application/json")
                .and()
                .body(new CreatePostRequest("sample title", "sample content"))
                .when()
                .post("/api/posts")
                .then()
                .extract()
                .response();

        assertEquals(201, createPostResponse.statusCode());
        ExternalPostResponse createdPost = createPostResponse.body().as(ExternalPostResponse.class);
        assertNotNull(createdPost);
        assertEquals("sample title", createdPost.title());
        assertEquals("sample content", createdPost.content());

        // Retrieve post by id
        Response getPostByIdResponse = given()
                .get("/api/posts/" + createdPost.postId().toString())
                .then()
                .extract()
                .response();

        assertEquals(200, getPostByIdResponse.statusCode());
        ExternalPostResponse postById = getPostByIdResponse.body().as(ExternalPostResponse.class);
        assertNotNull(postById);
        assertEquals("sample title", postById.title());
        assertEquals("sample content", postById.content());

        // Retrieve all post
        given().get("/api/posts")
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.is(1));
    }
}