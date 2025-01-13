package com.gym.controller;

import com.gym.model.Demo;
import com.gym.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/demo")
@CrossOrigin
public class DemoController {
    @Autowired
    private DemoService demoService;
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/book")
    public String add(@RequestBody Demo demo){
        demoService.saveDemo(demo);
        return null;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/findBook")
    public List<Demo> getAllDemos(){
        return demoService.getAllDemos();

    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/findBookByQuery")
    public List<Demo> findDemosByQuery(@RequestParam(value = "query", required = false) String query) {
        return demoService.getDemosByQuery(query);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/updateBook/{id}")
    public Demo updateDemo(@RequestBody Demo demo, @PathVariable("id") Integer id)
    {
        return demoService.updateDemo(demo,id);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/delete/{id}")
    public String deleteDemoById(@PathVariable("id") Integer id)
    {
        demoService.deleteDemoById(id);

        return "Deleted Successfully";
    }
}
