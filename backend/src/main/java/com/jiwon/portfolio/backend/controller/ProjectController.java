package com.jiwon.portfolio.backend.controller;

import com.jiwon.portfolio.backend.entity.Project;
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
    public List<Project> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Project findById(
            @PathVariable Long id) {

        return service.findById(id);
    }

    @PostMapping
    public Project create(
            @RequestBody Project project) {

        return service.create(project);
    }

    @PutMapping("/{id}")
    public Project update(
            @PathVariable Long id,
            @RequestBody Project project) {

        return service.update(
                id,
                project);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }
}
