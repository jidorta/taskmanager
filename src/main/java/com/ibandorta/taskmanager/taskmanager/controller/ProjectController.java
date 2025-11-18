package com.ibandorta.taskmanager.taskmanager.controller;


import com.ibandorta.taskmanager.taskmanager.dto.ApiResponse;
import com.ibandorta.taskmanager.taskmanager.model.Project;
import com.ibandorta.taskmanager.taskmanager.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {


    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Project>>create(@Valid @RequestBody Project project){
        Project saved = projectService.create(project);

        return new ResponseEntity<>(
                ApiResponse.success(201,saved),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Project>>> findAll() {
        List<Project> list = projectService.findAll();
        return ResponseEntity.ok(ApiResponse.success(200,list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Project>> findById(@PathVariable  Long id){
        Project project = projectService.findById(id);

        return ResponseEntity.ok(
                ApiResponse.success(200,project)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Project>>update(@PathVariable Long id, @Valid @RequestBody Project project){
        Project updated = projectService.update(id, project);
        return ResponseEntity.ok(ApiResponse.success(200,updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>>delete(@PathVariable Long id){
        projectService.delete(id);
        return ResponseEntity.ok(
                ApiResponse.success(200,null)
        );
    }





}
