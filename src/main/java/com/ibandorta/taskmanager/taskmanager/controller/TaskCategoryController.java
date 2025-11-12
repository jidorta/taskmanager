package com.ibandorta.taskmanager.taskmanager.controller;


import com.ibandorta.taskmanager.taskmanager.model.TaskCategory;
import com.ibandorta.taskmanager.taskmanager.repository.TaskCategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class TaskCategoryController {
    private final TaskCategoryRepository categoryRepo;

    public TaskCategoryController(TaskCategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping
    public List<TaskCategory> getAllCategories(){
        return categoryRepo.findAll();
    }

    @PostMapping
    public TaskCategory createCategory(@RequestBody TaskCategory category) {
        return categoryRepo.save(category);
    }

    @GetMapping("/{id}")
    public TaskCategory getCategory(@PathVariable Long id) {
        return categoryRepo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public TaskCategory updateCategory(@PathVariable Long id, @RequestBody TaskCategory updatedCategory) {
        TaskCategory category = categoryRepo.findById(id).orElseThrow();
        category.setName(updatedCategory.getName());
        return categoryRepo.save(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@ PathVariable Long id) {
        categoryRepo.deleteById(id);
    }
}
