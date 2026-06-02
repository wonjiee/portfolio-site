package com.jiwon.portfolio.backend.controller;

import com.jiwon.portfolio.backend.entity.Project;
import com.jiwon.portfolio.backend.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/featured")
    public List<Project> findFeatured() {
        return service.findFeatured();
    }

    @GetMapping("/{id}")
    public Project findById(
            @PathVariable Long id) {

        return service.findById(id);
    }

    @PostMapping
    public Project create(
            @RequestBody Project project) {

        try {
            return service.create(project);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }

    @PutMapping("/{id}")
    public Project update(
            @PathVariable Long id,
            @RequestBody Project project) {

        try {
            return service.update(
                    id,
                    project);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }
}
