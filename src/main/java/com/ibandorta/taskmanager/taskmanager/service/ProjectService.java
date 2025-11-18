package com.ibandorta.taskmanager.taskmanager.service;

import com.ibandorta.taskmanager.taskmanager.model.Project;

import java.util.List;

public interface ProjectService {
    Project create(Project project);
    Project update (Long id, Project project);
    void delete(Long id);
    Project findById(Long id);
    List<Project> findAll();
}
