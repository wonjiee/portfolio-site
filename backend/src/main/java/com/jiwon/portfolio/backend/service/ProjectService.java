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

    private final ProjectRepository repository;

    public List<Project> findAll() {
        return repository.findAll(
                Sort.by(
                        Sort.Direction.DESC,
                        "id"
                )
        );
    }

    public Project findById(Long id) {

        return repository.findById(id)
                .orElseThrow();
    }

    public Project create(Project project) {

        return repository.save(project);
    }

    public Project update(
            Long id,
            Project updatedProject) {

        Project project =
                repository.findById(id)
                        .orElseThrow();

        project.setTitle(
                updatedProject.getTitle());

        project.setTechStack(
                updatedProject.getTechStack());

        project.setSummary(
                updatedProject.getSummary());
        project.setDescription(
                updatedProject.getDescription());
        project.setGithubUrl(
                updatedProject.getGithubUrl());

        project.setDemoUrl(
                updatedProject.getDemoUrl());

        project.setImageUrl(
                updatedProject.getImageUrl());

        project.setPeriod(
                updatedProject.getPeriod());

        project.setRole(
                updatedProject.getRole());

        return repository.save(project);
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
}
