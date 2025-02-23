package com.gym.controller;


import com.gym.model.AppUser;
import com.gym.model.LoginDto;
import com.gym.model.RegisterDto;
import com.gym.repository.AppUserRepository;
import com.gym.service.JWTService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/profile")
    public ResponseEntity<Object> profile(Authentication auth){
        var response = new HashMap<String,Object>();
        response.put("Username",auth.getName());
        response.put("Authorities",auth.getAuthorities());
        var appUser = appUserRepository.findByEmail(auth.getName());
        response.put("User",appUser);

        return ResponseEntity.ok(response);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @Valid @RequestBody RegisterDto registerDto
            ,BindingResult result){

        if(result.hasErrors()){
            var errorsList = result.getAllErrors();
            var errorsMap = new HashMap<String, String>();
            for(int i=0;i< errorsList.size();i++){
                var error=(FieldError) errorsList.get(i);
                errorsMap.put(error.getField(),error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorsMap);
        }

        AppUser appUser = new AppUser();
        appUser.setFirstName(registerDto.getFirstName());
        appUser.setLastName(registerDto.getLastName());
        appUser.setEmail(registerDto.getEmail());
        appUser.setPhone(registerDto.getPhone());
        appUser.setAddress(registerDto.getAddress());
        appUser.setRole("client");
        appUser.setCreatedAt(new Date());

        var bCryptEncoder = new BCryptPasswordEncoder();
        appUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

        try{
            // check if username/email are used or not
            var otherUser = appUserRepository.findByEmail(registerDto.getEmail());
            if (otherUser !=null){
                return ResponseEntity.badRequest().body("Email address already used");
            }
            appUserRepository.save(appUser);
            String jwtToken = jwtService.createJwtToken(appUser);

            var response = new HashMap<String, Object>();
            response.put("token",jwtToken);
            response.put("user",appUser);

            return ResponseEntity.ok(response);
        }
        catch(Exception ex){
            System.out.println("There is an Exception :");
            ex.printStackTrace();

        }
        return ResponseEntity.badRequest().body("Error");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @Valid @RequestBody LoginDto loginDto
            , BindingResult result){

        if(result.hasErrors()){
            var errorsList = result.getAllErrors();
            var errorsMap = new HashMap<String, String>();
            for(int i=0;i< errorsList.size();i++){
                var error=(FieldError) errorsList.get(i);
                errorsMap.put(error.getField(),error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorsMap);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );
            AppUser appUser = appUserRepository.findByEmail(loginDto.getEmail());
            String jwtToken = jwtService.createJwtToken(appUser);

            var response = new HashMap<String, Object>();
            response.put("token", jwtToken);
            response.put("user", appUser);
            return ResponseEntity.ok(response);
        }

        catch (Exception ex){
            System.out.println("There is an Exception :");
            ex.printStackTrace();
        }

        return ResponseEntity.badRequest().body("Bad username or password");
    }

}


