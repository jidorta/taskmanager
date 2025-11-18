package com.ibandorta.taskmanager.taskmanager.service.impl;

import com.ibandorta.taskmanager.taskmanager.model.Project;
import com.ibandorta.taskmanager.taskmanager.repository.ProjectRepository;
import com.ibandorta.taskmanager.taskmanager.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project update(Long id, Project project) {
        Project existing = projectRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Project not found"));

        existing.setName(project.getName());
        existing.setDescription(project.getDescription());

        return projectRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!projectRepository.existsById(id)){
            throw new RuntimeException("Project not found");
        }
        projectRepository.deleteById(id);

    }

    @Override
    public Project findById(Long id) {

        return projectRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Project not found"));
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
}
