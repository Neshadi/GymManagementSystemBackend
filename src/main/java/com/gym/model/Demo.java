package com.gym.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Demo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id1;
    private String fullName;
    private String email;

    private String selectedDate;
    private String selectedTime;
    private String selectedDemo;



    public Demo() {
    }

}

