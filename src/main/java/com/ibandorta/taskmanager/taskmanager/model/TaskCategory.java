package com.ibandorta.taskmanager.taskmanager.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TaskCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public TaskCategory(){

    }

    public TaskCategory(Long id, String name, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
