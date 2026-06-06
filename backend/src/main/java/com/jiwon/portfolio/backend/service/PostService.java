package com.jiwon.portfolio.backend.service;

import com.jiwon.portfolio.backend.entity.Post;
import com.jiwon.portfolio.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final Sort LIST_SORT = Sort.by(
            Sort.Order.desc("pinned"),
            Sort.Order.asc("sortOrder"),
            Sort.Order.desc("createdAt")
    );

    private final PostRepository repository;

    public List<Post> findAll() {

        return repository.findAll(LIST_SORT);
    }

    public Post findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Post not found"
                ));
    }

    public Post create(Post post) {

        if (post.getSortOrder() <= 0) {
            post.setSortOrder(nextSortOrder());
        }

        return repository.save(post);
    }

    public Post update(
            Long id,
            Post source) {

        Post target =
                repository.findById(id)
                        .orElseThrow();

        target.setTitle(source.getTitle());
        target.setContent(source.getContent());
        target.setSortOrder(source.getSortOrder());
        target.setPinned(source.isPinned());

        return repository.save(target);
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

    private int nextSortOrder() {

        return repository.findAll().stream()
                .mapToInt(Post::getSortOrder)
                .max()
                .orElse(0) + 1;
    }
}
