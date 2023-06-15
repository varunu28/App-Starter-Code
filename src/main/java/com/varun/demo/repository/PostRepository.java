package com.varun.demo.repository;

import com.varun.demo.model.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.varun.demo.repository.PostQueries.CREATE_POST_QUERY;
import static com.varun.demo.repository.PostQueries.GET_ALL_POSTS_QUERY;

@Repository
public class PostRepository {

    private final EntityManager entityManager;

    public PostRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void create(String title, String content) {
        Query query = entityManager.createNativeQuery(CREATE_POST_QUERY)
                .setParameter(1, title)
                .setParameter(2, content);
        query.executeUpdate();
    }

    public List<Post> findAll() {
        Query query = entityManager.createNativeQuery(GET_ALL_POSTS_QUERY);
        List<?> resultList = query.getResultList();
        return resultList
                .stream()
                .map(this::convertToPost)
                .collect(Collectors.toList());
    }

    private Post convertToPost(Object result) {
        Object[] row = (Object[]) result;
        Integer id = (Integer) row[0];
        String title = (String) row[1];
        String content = (String) row[2];
        return new Post(id, title, content);
    }
}