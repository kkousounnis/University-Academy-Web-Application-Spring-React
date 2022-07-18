package com.spring.boot.coodle.dao;

import com.spring.boot.coodle.entities.Trainer;
import com.spring.boot.coodle.repository.TrainerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerDaoImpl implements TrainerDao {

    @Autowired
    TrainerRepository trainerRepository;

    public List<Trainer> findAllTrainers() {

        return (trainerRepository.findAll());
    }

    public Trainer save(Trainer trainer) {
        return (trainerRepository.save(trainer));
    }

    public Trainer update(int id, Trainer trainer) {

        Trainer myTrainer = new Trainer();
        /**
         * Update by field You can find it based the fields
         */
        myTrainer = trainer;
        return (trainerRepository.save(trainer));
    }
    
    public Optional<Trainer> findById(int id){
        
        return (trainerRepository.findById(id));
    }

    public void delete(int id) {
        trainerRepository.deleteById(id);
    }

}
