package com.jiwon.portfolio.backend.service;

import com.jiwon.portfolio.backend.entity.Post;
import com.jiwon.portfolio.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;

    public List<Post> findAll() {

        return repository.findAll();
    }

    public Post findById(Long id) {

        return repository.findById(id)
                .orElseThrow();
    }

    public Post save(Post post) {

        return repository.save(post);
    }

    public Post update(
            Long id,
            Post post) {

        Post target =
                repository.findById(id)
                        .orElseThrow();

        target.setTitle(
                post.getTitle());

        target.setContent(
                post.getContent());

        return repository.save(target);
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
}