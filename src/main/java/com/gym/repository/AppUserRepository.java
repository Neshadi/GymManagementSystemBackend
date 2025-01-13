package com.gym.repository;


import com.gym.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    public AppUser findByEmail(String email);
}
