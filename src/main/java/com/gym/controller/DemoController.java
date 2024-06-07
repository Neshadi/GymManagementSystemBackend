package com.gym.controller;

import com.gym.model.Demo;
import com.gym.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
@CrossOrigin
public class DemoController {
    @Autowired
    private DemoService demoService;
    @PostMapping("/book")
    public String add(@RequestBody Demo demo){
        demoService.saveDemo(demo);
        return null;
    }
    @GetMapping("/findBook")
    public List<Demo> getAllDemos(){
        return demoService.getAllDemos();

    }
    @GetMapping("/findBookByQuery")
    public List<Demo> findDemosByQuery(@RequestParam(value = "query", required = false) String query) {
        return demoService.getDemosByQuery(query);
    }

    @PutMapping("/updateBook/{id}")
    public Demo updateDemo(@RequestBody Demo demo, @PathVariable("id") Integer id)
    {
        return demoService.updateDemo(demo,id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDemoById(@PathVariable("id") Integer id)
    {
        demoService.deleteDemoById(id);

        return "Deleted Successfully";
    }
}
