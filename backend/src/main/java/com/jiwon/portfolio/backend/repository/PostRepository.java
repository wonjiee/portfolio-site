package com.jiwon.portfolio.backend.repository;

import com.jiwon.portfolio.backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository
        extends JpaRepository<Post, Long> {
}