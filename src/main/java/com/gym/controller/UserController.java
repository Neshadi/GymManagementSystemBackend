package com.gym.controller;

import com.gym.model.User;
import com.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public String add(@RequestBody User user){
        userService.saveUsers(user);
        return null;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/findAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();

    }
}
