package com.gym.service;

import com.gym.model.Demo;


import java.util.List;


public interface DemoService {
    public Demo saveDemo(Demo demo);
    public List<Demo> getAllDemos();

    Demo updateDemo(Demo demo,Integer id);
    void deleteDemoById(Integer id);

//    public List<Demo> getDemosByQuery(Integer id, String fullName, String email, String selectedDate, String selectedTime, String selectedDemo);
    public List<Demo> getDemosByQuery(String query);
}
