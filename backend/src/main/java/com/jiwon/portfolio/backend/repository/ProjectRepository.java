package com.jiwon.portfolio.backend.repository;

import com.jiwon.portfolio.backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository
        extends JpaRepository<Project, Long> {

    List<Project> findByFeaturedTrueOrderBySortOrderAscIdDesc();

    long countByFeaturedTrue();
}
