package com.spring.boot.coodle.dao;

import com.spring.boot.coodle.entities.Trainer;
import java.util.List;

public interface TrainerDao {

    public List<Trainer> findAllTrainers();

    public Trainer save(Trainer Trainer);

    public Trainer update(int id, Trainer trainer);

    public void delete(int id);

}
