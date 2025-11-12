package com.ibandorta.taskmanager.taskmanager.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TaskComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="task_id")
    private Task task;

    public TaskComment(){

    }

    public TaskComment(Long id, String text, LocalDateTime createdAt, Task task) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
