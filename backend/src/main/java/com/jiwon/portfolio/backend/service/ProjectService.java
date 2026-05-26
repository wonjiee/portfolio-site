package com.jiwon.portfolio.backend.service;

import com.jiwon.portfolio.backend.entity.Project;
import com.jiwon.portfolio.backend.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository repository;

    public List<Project> getProjects(){

        return repository.findAll();
    }

    public Project createProject(
            Project project){

        return repository.save(project);
    }

    public Project updateProject(
            Long id,
            Project updatedProject){

        Project project =
                repository.findById(id)
                        .orElseThrow();

        project.setTitle(
                updatedProject.getTitle());

        project.setTechStack(
                updatedProject.getTechStack());

        project.setDescription(
                updatedProject.getDescription());

        return repository.save(project);
    }

    public void deleteProject(Long id){

        repository.deleteById(id);
    }

}