package com.ibandorta.taskmanager.taskmanager.controller;


import com.ibandorta.taskmanager.taskmanager.dto.ApiResponse;
import com.ibandorta.taskmanager.taskmanager.model.Task;
import com.ibandorta.taskmanager.taskmanager.repository.TaskRepository;
import com.ibandorta.taskmanager.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins ="*")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    //Obtener todas las tareas
    @GetMapping
    public ResponseEntity<ApiResponse<List<Task>>>getAllTask(){
        ApiResponse<List<Task>>response = new ApiResponse<>();
        response.setTimestamp(LocalDateTime.now());

        List<Task>tasks = taskRepository.findAll();
        response.setStatusCode(HttpStatus.OK.value());
        response.setData(tasks);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Task>> createTask(@Valid @RequestBody Task newtask) {
        Task savedTask = taskRepository.save(newtask);

        ApiResponse<Task> response = new ApiResponse<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setData(savedTask);
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


 //-----------GET TASK BY ID --------------------------
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>>getTaskById(@PathVariable Long id){
        ApiResponse<Task> response = new ApiResponse<>();
        response.setTimestamp(LocalDateTime.now());

        return taskRepository.findById(id)
                .map(task -> {
                    response.setStatusCode(HttpStatus.OK.value());
                    response.setData(task);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(()->{
                   response.setStatusCode(HttpStatus.NOT_FOUND.value());
                   response.setError("Tarea no encontrada");
                   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

                });
    }

    // Actualizar una tarea
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> updateTask(@PathVariable Long id, @RequestBody Task updatedTask){
        ApiResponse<Task> response = new ApiResponse<>();
        response.setTimestamp(LocalDateTime.now());

        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(updatedTask.getTitle());
                    existingTask.setDescription(updatedTask.getDescription());
                    // Si tienes más campos, se actualizan aquí.

                    Task updated = taskRepository.save(existingTask);

                    response.setStatusCode(HttpStatus.OK.value());
                    response.setData(updated);

                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    response.setStatusCode(HttpStatus.NOT_FOUND.value());
                    response.setError("Tarea no encontrada");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }


    //Eliminar una tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>>deleteTask(@PathVariable Long id){
      ApiResponse<Void> response = new ApiResponse<>();
      response.setTimestamp(LocalDateTime.now());

      if (!taskRepository.existsById(id)){
          response.setStatusCode(HttpStatus.NOT_FOUND.value());
          response.setError("Tarea no encontrada");
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
      }

      taskRepository.deleteById(id);
      response.setStatusCode(HttpStatus.NO_CONTENT.value());
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);

    }
}
