package com.jiwon.portfolio.backend.repository;

import com.jiwon.portfolio.backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository
        extends JpaRepository<Project, Long> {
}
