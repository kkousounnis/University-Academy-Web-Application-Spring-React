package com.spring.boot.coodle.services;

import com.spring.boot.coodle.dao.TrainerDaoImpl;
import com.spring.boot.coodle.entities.Trainer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerDaoImpl trainerDao;

    @Override
    public List<Trainer> findAllTrainers() {

        return trainerDao.findAllTrainers();
    }

}
