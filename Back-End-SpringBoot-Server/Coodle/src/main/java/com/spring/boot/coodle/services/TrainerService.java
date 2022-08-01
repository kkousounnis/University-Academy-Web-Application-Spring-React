package com.spring.boot.coodle.services;

import com.spring.boot.coodle.entities.Trainer;
import com.spring.boot.coodle.entities.dto.requests.TrainerRequest;
import java.util.List;
import java.util.Optional;

public interface TrainerService {
    
    public List<Trainer> findAllTrainers();
    
    public TrainerRequest update(int id, TrainerRequest trainerRequest);
    
    public Optional<Trainer> findById(int id);

    public void save(TrainerRequest trainer);
 
    public void delete(int id);
    
    
}
