package com.ibandorta.taskmanager.taskmanager.controller;


import com.ibandorta.taskmanager.taskmanager.dto.ApiResponse;
import com.ibandorta.taskmanager.taskmanager.model.TaskCategory;
import com.ibandorta.taskmanager.taskmanager.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class TaskCategoryController {

    @Autowired
    private TaskCategoryRepository categoryRepository;



    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskCategory>>> getAllCategories(){
        ApiResponse<List<TaskCategory>>response = new ApiResponse<>();
        response.setTimestamp(LocalDateTime.now());

        List<TaskCategory> categories = categoryRepository.findAll();

        response.setStatusCode(HttpStatus.OK.value());
        response.setData(categories);

        return ResponseEntity.ok(response);

    }


    @PostMapping
    public ResponseEntity<ApiResponse<TaskCategory>> createCategory(@RequestBody TaskCategory newcategory) {
        TaskCategory savedCategory = categoryRepository.save(newcategory);

        ApiResponse<TaskCategory> response = new ApiResponse<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setData(savedCategory);
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskCategory>> getCategory(@PathVariable Long id) {
        ApiResponse<TaskCategory> response = new ApiResponse<>();
        response.setTimestamp(LocalDateTime.now());

        return categoryRepository.findById(id)
                .map(category -> {
                    response.setStatusCode(HttpStatus.OK.value());
                    response.setData(category);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    response.setStatusCode(HttpStatus.NOT_FOUND.value());
                    response.setError("Categoría no encontrada");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskCategory>> updateCategory(@PathVariable Long id, @RequestBody TaskCategory updatedCategory) {

        ApiResponse<TaskCategory> response = new ApiResponse<>();
        response.setTimestamp(LocalDateTime.now());

        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(updatedCategory.getName());

                    TaskCategory updated = categoryRepository.save(existingCategory);

                    response.setStatusCode(HttpStatus.OK.value());
                    response.setData(updated);

                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    response.setStatusCode(HttpStatus.NOT_FOUND.value());
                    response.setError("Categoría no encontrada");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }


    // ----------------- DELETE CATEGORY -----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        ApiResponse<Void> response = new ApiResponse<>();
        response.setTimestamp(LocalDateTime.now());

        if (!categoryRepository.existsById(id)) {
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setError("Categoría no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        categoryRepository.deleteById(id);
        response.setStatusCode(HttpStatus.NO_CONTENT.value());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
