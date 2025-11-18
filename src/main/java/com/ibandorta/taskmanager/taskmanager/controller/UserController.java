package com.ibandorta.taskmanager.taskmanager.controller;


import com.ibandorta.taskmanager.taskmanager.dto.ApiResponse;
import com.ibandorta.taskmanager.taskmanager.model.User;
import com.ibandorta.taskmanager.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private  UserRepository userRepo;



    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers(){
        ApiResponse<List<User>> response = new ApiResponse<>();
        response.setTimestamp(LocalDateTime.now());

        List<User> users = userRepo.findAll();
        response.setStatusCode(HttpStatus.OK.value());
        response.setData(users);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user){
        User savedUser = userRepo.save(user);

        ApiResponse<User> response = new ApiResponse<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setData(savedUser);
        response.setError(null);
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable Long id) {
        User user = userRepo.findById(id)
                .orElse(null);
        ApiResponse<User> response = new ApiResponse<>();
        response.setTimestamp(LocalDateTime.now());

        if (user == null) {
             response.setStatusCode(HttpStatus.NOT_FOUND.value());
             response.setError("Usuario no encontrado");
             response.setData(null);
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.setStatusCode(HttpStatus.OK.value());
        response.setData(user);
        response.setError(null);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        User user = userRepo.findById(id).orElseThrow();
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        return userRepo.save(user);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id){
        ApiResponse<Void> response = new ApiResponse<>();
        response.setTimestamp(LocalDateTime.now());

        if(!userRepo.existsById(id)){
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setError("Usuario no encontrado");
            ResponseEntity.status(HttpStatus.NO_CONTENT.value());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        userRepo.deleteById(id);
        response.setStatusCode(HttpStatus.NO_CONTENT.value());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }


}
