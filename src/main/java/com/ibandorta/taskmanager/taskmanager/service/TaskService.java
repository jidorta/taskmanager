package com.ibandorta.taskmanager.taskmanager.service;

import com.ibandorta.taskmanager.taskmanager.repository.TaskRepository;
import com.ibandorta.taskmanager.taskmanager.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    //Obtener todas las tareas
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    //Obtener una tarea por su ID
    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public Task createTask(Task task){
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdateAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    //Actualizar una tarea existente
    public Task updateTask(Long id, Task updatedTask){
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setStatus(updatedTask.getStatus());
                    task.setUpdateAt(LocalDateTime.now());
                    return taskRepository.save(task);
                })
                .orElseThrow(()-> new RuntimeException("Tarea no encontrada con id: " + id));
    }

    // Eliminar tarea
    public void deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tarea no encontrada con id: " + id);
        }
    }
}
