package com.ibandorta.taskmanager.taskmanager.model;


import jakarta.persistence.*;

import com.ibandorta.taskmanager.taskmanager.model.Status;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name= "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name ="category_id")
    private TaskCategory category;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskComment> comments;

    public Task(){

    }

    public Task(Long id, String title, String description, Status status, LocalDateTime createdAt, LocalDateTime updateAt, User user, TaskCategory category, List<TaskComment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.user = user;
        this.category = category;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public List<TaskComment> getComments() {
        return comments;
    }

    public void setComments(List<TaskComment> comments) {
        this.comments = comments;
    }
}
