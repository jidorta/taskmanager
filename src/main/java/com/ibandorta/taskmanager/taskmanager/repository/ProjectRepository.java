package com.ibandorta.taskmanager.taskmanager.repository;

import com.ibandorta.taskmanager.taskmanager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
