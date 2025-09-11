package com.gym.controller;

import com.gym.model.Demo;
import com.gym.model.DemoList;
import com.gym.service.DemoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demoList")
@CrossOrigin
public class DemoListController {
    @Autowired
    public DemoListService demoListService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/findDemoList")
    public List<DemoList> getAllDemos(){
        return demoListService.getAllDemos();

    }
}
