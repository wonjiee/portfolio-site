package com.jiwon.portfolio.backend.service;

import com.jiwon.portfolio.backend.entity.Project;
import com.jiwon.portfolio.backend.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private static final int MAX_FEATURED = 3;

    private static final Sort LIST_SORT = Sort.by(
            Sort.Order.asc("sortOrder"),
            Sort.Order.desc("id")
    );

    private final ProjectRepository repository;

    public List<Project> findAll() {

        return repository.findAll(LIST_SORT);
    }

    public List<Project> findFeatured() {

        List<Project> featured =
                repository.findByFeaturedTrueOrderBySortOrderAscIdDesc();

        if (featured.size() <= MAX_FEATURED) {
            return featured;
        }

        return featured.subList(0, MAX_FEATURED);
    }

    public Project findById(Long id) {

        return repository.findById(id)
                .orElseThrow();
    }

    public Project create(Project project) {

        if (project.getSortOrder() <= 0) {
            project.setSortOrder(nextSortOrder());
        }

        validateFeatured(
                project.isFeatured(),
                null
        );

        return repository.save(project);
    }

    public Project update(
            Long id,
            Project updatedProject) {

        Project project =
                repository.findById(id)
                        .orElseThrow();

        validateFeatured(
                updatedProject.isFeatured(),
                id
        );

        applyFields(project, updatedProject);

        return repository.save(project);
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

    private void applyFields(
            Project project,
            Project source) {

        project.setTitle(source.getTitle());
        project.setTechStack(source.getTechStack());
        project.setSummary(source.getSummary());
        project.setDescription(source.getDescription());
        project.setGithubUrl(source.getGithubUrl());
        project.setDemoUrl(source.getDemoUrl());
        project.setImageUrl(source.getImageUrl());
        project.setPeriod(source.getPeriod());
        project.setRole(source.getRole());
        project.setSortOrder(source.getSortOrder());
        project.setFeatured(source.isFeatured());
    }

    private int nextSortOrder() {

        return repository.findAll().stream()
                .mapToInt(Project::getSortOrder)
                .max()
                .orElse(0) + 1;
    }

    private void validateFeatured(
            boolean featured,
            Long currentId) {

        if (!featured) {
            return;
        }

        long count = repository.countByFeaturedTrue();

        if (currentId != null) {
            Project current = repository.findById(currentId)
                    .orElseThrow();

            if (current.isFeatured()) {
                count--;
            }
        }

        if (count >= MAX_FEATURED) {
            throw new IllegalArgumentException(
                    "추천 프로젝트는 최대 3개까지 설정할 수 있습니다."
            );
        }
    }
}
