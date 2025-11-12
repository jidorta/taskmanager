package com.ibandorta.taskmanager.taskmanager.repository;

import com.ibandorta.taskmanager.taskmanager.model.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCategoryRepository  extends JpaRepository<TaskCategory, Long> {
}
