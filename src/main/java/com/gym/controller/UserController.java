package com.gym.controller;

import com.gym.model.User;
import com.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/add")
    public String add(@RequestBody User user){
        userService.saveUsers(user);
        return null;
    }

    @GetMapping("/findAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();

    }
}
