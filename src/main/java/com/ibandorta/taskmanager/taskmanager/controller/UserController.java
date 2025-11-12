package com.ibandorta.taskmanager.taskmanager.controller;


import com.ibandorta.taskmanager.taskmanager.model.User;
import com.ibandorta.taskmanager.taskmanager.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepo.save(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userRepo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        User user = userRepo.findById(id).orElseThrow();
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        return userRepo.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepo.deleteById(id);
    }


}
