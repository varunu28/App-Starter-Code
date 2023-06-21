package com.varun.demo.posts.repository;

import com.varun.demo.posts.model.Post;
import com.varun.demo.posts.request.ExternalPostResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.varun.demo.posts.repository.PostQueries.*;

@Repository
public class PostRepository {

    private final EntityManager entityManager;

    public PostRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public ExternalPostResponse create(String title, String content) {
        UUID uuid = UUID.randomUUID();
        Query query = entityManager.createNativeQuery(CREATE_POST_QUERY)
                .setParameter(1, uuid)
                .setParameter(2, title)
                .setParameter(3, content);
        query.executeUpdate();
        return new ExternalPostResponse(uuid, title, content);
    }

    public List<ExternalPostResponse> findAll() {
        Query query = entityManager.createNativeQuery(GET_ALL_POSTS_QUERY);
        List<?> resultList = query.getResultList();
        return resultList
                .stream()
                .map(this::convertToPost)
                .map(Post::convertToExternalPostResponse)
                .collect(Collectors.toList());
    }

    public Optional<ExternalPostResponse> findPostById(UUID id) {
        Query query = entityManager.createNativeQuery(GET_POST_BY_ID)
                .setParameter(1, id);
        List<?> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(convertToPost(resultList.get(0)).convertToExternalPostResponse());
    }

    private Post convertToPost(Object result) {
        Object[] row = (Object[]) result;
        Integer id = (Integer) row[0];
        UUID externalId = (UUID) row[1];
        String title = (String) row[2];
        String content = (String) row[3];
        return new Post(id, externalId, title, content);
    }
}