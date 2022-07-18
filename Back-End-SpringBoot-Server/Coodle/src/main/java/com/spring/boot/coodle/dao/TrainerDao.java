package com.spring.boot.coodle.dao;

import com.spring.boot.coodle.entities.Trainer;
import java.util.List;
import java.util.Optional;

public interface TrainerDao {

    public List<Trainer> findAllTrainers();

    public Trainer save(Trainer Trainer);

    public Trainer update(int id, Trainer trainer);
    
    public Optional<Trainer> findById(int id);

    public void delete(int id);

}
