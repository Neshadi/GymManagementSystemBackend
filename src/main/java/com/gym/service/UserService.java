package com.gym.service;

import com.gym.model.User;

import java.util.List;

public interface UserService {
    public User saveUsers(User user);
    public List<User> getAllUsers();
}

