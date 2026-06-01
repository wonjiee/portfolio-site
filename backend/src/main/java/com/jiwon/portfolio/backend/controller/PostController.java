package com.jiwon.portfolio.backend.controller;

import com.jiwon.portfolio.backend.entity.Post;
import com.jiwon.portfolio.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping
    public List<Post> findAll() {

        return service.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(
            @PathVariable Long id) {

        return service.findById(id);
    }

    @PostMapping
    public Post save(
            @RequestBody Post post) {

        return service.save(post);
    }

    @PutMapping("/{id}")
    public Post update(
            @PathVariable Long id,
            @RequestBody Post post) {

        return service.update(
                id,
                post);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }
}