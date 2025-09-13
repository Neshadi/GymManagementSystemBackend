package com.gym.controller;

import com.gym.model.DemoList;
import com.gym.service.DemoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demoList")
@CrossOrigin(origins = { "http://localhost:3000", "https://Neshadi.github.io/GymManagementSystemFrontend" })
public class DemoListController {

    @Autowired
    private DemoListService demoListService;

    @GetMapping("/findDemoList")
    public List<DemoList> getAllDemos() {
        return demoListService.getAllDemos();
    }
}
