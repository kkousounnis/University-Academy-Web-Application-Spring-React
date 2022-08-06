package com.spring.boot.coodle.services;

import com.spring.boot.coodle.entities.Trainer;
import com.spring.boot.coodle.entities.dto.requests.TrainerRequest;
import java.util.List;

public interface TrainerService {
    
    public List<Trainer> findAllTrainers();
    
    public void update(int id, TrainerRequest trainerRequest);
    
    public Trainer findById(int id);

    public void save(TrainerRequest trainer);
 
    public void delete(int id);
    
    
}
