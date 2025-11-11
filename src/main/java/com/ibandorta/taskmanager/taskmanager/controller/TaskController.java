package com.ibandorta.taskmanager.taskmanager.controller;


import com.ibandorta.taskmanager.taskmanager.model.Task;
import com.ibandorta.taskmanager.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins ="*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    //Obtener todas las tareas
    @GetMapping
    public List<Task>getAllTask(){
        return taskService.getAllTasks();
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task>getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar una tarea
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask){
        try{
            Task task = taskService.updateTask(id, updatedTask);
            return ResponseEntity.ok(task);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }

    //Eliminar una tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteTask(@PathVariable Long id){
        try{
                taskService.deleteTask(id);
                return ResponseEntity.noContent().build();
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
