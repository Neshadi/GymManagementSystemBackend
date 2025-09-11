package com.gym.service;

import com.gym.model.DemoList;
import com.gym.repository.DemoListRepository;
import com.gym.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DemoListServiceImpl implements DemoListService{

    @Autowired
    private DemoListRepository demoListRepository;
    @Override
    public List<DemoList> getAllDemos() {
        return demoListRepository.findAll();
    }
}
