package com.ibandorta.taskmanager.taskmanager.repository;

import com.ibandorta.taskmanager.taskmanager.model.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCommentRepository extends JpaRepository<TaskComment,Long> {
}
