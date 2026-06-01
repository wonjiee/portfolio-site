package com.jiwon.portfolio.backend.controller;

import com.jiwon.portfolio.backend.entity.Project;
import com.jiwon.portfolio.backend.repository.ProjectRepository;

import com.jiwon.portfolio.backend.service.ProjectService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @GetMapping
    public List<Project> getProjects(){

        return service.getProjects();
    }

    @PostMapping
    public Project createProject(
            @RequestBody Project project){

        return service.createProject(project);
    }

    @GetMapping("/{id}")
    public Project getProject(
            @PathVariable Long id){

        return service.getProject(id);
    }

    @PutMapping("/{id}")
    public Project updateProject(
            @PathVariable Long id,
            @RequestBody Project project){

        return service.updateProject(
                id,
                project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(
            @PathVariable Long id){

        service.deleteProject(id);
    }

}