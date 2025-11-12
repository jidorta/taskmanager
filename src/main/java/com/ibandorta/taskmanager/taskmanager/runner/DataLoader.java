package com.ibandorta.taskmanager.taskmanager.runner;

import com.ibandorta.taskmanager.taskmanager.model.*;
import com.ibandorta.taskmanager.taskmanager.repository.TaskCategoryRepository;
import com.ibandorta.taskmanager.taskmanager.repository.TaskCommentRepository;
import com.ibandorta.taskmanager.taskmanager.repository.TaskRepository;
import com.ibandorta.taskmanager.taskmanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepo;
    private final TaskCategoryRepository categoryRepo;
    private final TaskRepository taskRepo;
    private final TaskCommentRepository commentRepo;

    public DataLoader(UserRepository userRepo, TaskCategoryRepository categoryRepo, TaskRepository taskRepo, TaskCommentRepository commentRepo) {
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.taskRepo = taskRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        //Crear usuario
        User user = new User();
        user.setUsername("iván");
        user.setEmail("ivan@gmail.com");
        userRepo.save(user);


        //Crear categoria
        TaskCategory category = new TaskCategory();
        category.setName("Trabajo");
        categoryRepo.save(category);

        //Crear tarea
        Task task = new Task();
        task.setTitle("Preparar presentación");
        task.setDescription("Hacer demo de TaskManager");
        task.setStatus(Status.PENDING);
        task.setUser(user);
        task.setCategory(category);
        taskRepo.save(task);

        //Crear comentarios
        TaskComment comment1 = new TaskComment();
        comment1.setText("Primera versión lista");
        comment1.setCreatedAt(LocalDateTime.now());
        comment1.setTask(task);
        commentRepo.save(comment1);

        TaskComment comment2 = new TaskComment();
        comment2.setText("Revisar errores");
        comment2.setCreatedAt(LocalDateTime.now());
        comment2.setTask(task);
        commentRepo.save(comment2);


        //Asociar comentarios a la tarea
        task.setComments(List.of(comment1,comment2));
        taskRepo.save(task);

        System.out.println("Datos iniciales cargados correctamente!");

    }
}
