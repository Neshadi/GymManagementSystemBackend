package com.gym.service;

import com.gym.model.Demo;
import com.gym.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemoServiceImpl implements DemoService{
    @Autowired
    private DemoRepository demoRepository;

    @Override
    public Demo saveDemo(Demo demo) {
        return demoRepository.save(demo);
    }

    @Override
    public List<Demo> getAllDemos() {
        return demoRepository.findAll();
    }

//    @Override
//    public Demo updateDemo(Demo demo, Integer id)  {
//        Demo demoDB = demoRepository.findById(id).get();
//
//        return demoRepository.save(demoDB);
//    }
public Demo updateDemo(Demo newDemoData, Integer id) {
    return demoRepository.findById(id).map(demo -> {
        demo.setFullName(newDemoData.getFullName());
        demo.setEmail(newDemoData.getEmail());
        demo.setSelectedDate(newDemoData.getSelectedDate());
        demo.setSelectedTime(newDemoData.getSelectedTime());
        demo.setSelectedDemo(newDemoData.getSelectedDemo());
        return demoRepository.save(demo);
    }).orElseGet(() -> {
        newDemoData.setId(id);
        return demoRepository.save(newDemoData);
    });
}


    @Override
    public void deleteDemoById(Integer id) {
        demoRepository.deleteById(id);
    }

//    @Override
//    public List<Demo> getDemosByQuery(Integer id, String fullName, String email, String selectedDate, String selectedTime, String selectedDemo) {
//        return demoRepository.findByIdOrFullNameOrEmailOrSelectedDateOrSelectedTimeOrSelectedDemo(id,fullName,email,selectedDate,selectedTime,selectedDemo);
//    }

    public List<Demo> getDemosByQuery(String query) {
        List<Demo> demos = demoRepository.findAll();
        if (query == null || query.isEmpty()) {
            return demos;
        }

        return demos.stream()
                .filter(demo -> (demo.getFullName() != null && demo.getFullName().contains(query)) ||
                        (demo.getEmail() != null && demo.getEmail().contains(query)) ||
                        (demo.getSelectedDate() != null && demo.getSelectedDate().contains(query)) ||
                        (demo.getSelectedTime() != null && demo.getSelectedTime().contains(query)) ||
                        (demo.getSelectedDemo() != null && demo.getSelectedDemo().contains(query)))
                .collect(Collectors.toList());
    }



}
