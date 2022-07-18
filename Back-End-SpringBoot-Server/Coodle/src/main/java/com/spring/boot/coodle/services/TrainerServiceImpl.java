package com.spring.boot.coodle.services;

import com.spring.boot.coodle.dao.TrainerDaoImpl;
import com.spring.boot.coodle.dao.UserDaoImpl;
import com.spring.boot.coodle.entities.ERole;
import com.spring.boot.coodle.entities.Role;
import com.spring.boot.coodle.entities.Trainer;
import com.spring.boot.coodle.entities.User;
import com.spring.boot.coodle.entities.dto.requests.TrainerRequest;
import com.spring.boot.coodle.repository.RoleRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {
    
    @Autowired
    private TrainerDaoImpl trainerDao;
    
    @Autowired
    private UserDaoImpl userDao;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public List<Trainer> findAllTrainers() {
        
        return trainerDao.findAllTrainers();
    }
    
    @Override
    public TrainerRequest update(int id, TrainerRequest trainerRequest) {
        
        return (null);
    }
    
    @Override
    public Optional<Trainer> findById(int id) {
        
        return (trainerDao.findById(id));
    }
    
    @Override
    public void save(TrainerRequest trainerRequest) {
        Trainer trainer = new Trainer();
        User user = new User();
        user.setEmail(trainerRequest.getEmail());
        user.setFirstName(trainerRequest.getFirstName());
        user.setLastName(trainerRequest.getLastName());
        user.setPassword(trainerRequest.getPassword());
        Set<Role> roles = new HashSet<>();
        
        Role userRole = roleRepository.findByRole(ERole.ROLE_MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));        
        roles.add(userRole);
        user.setRoles(roles);
        user = userDao.save(user);
        
        System.err.println("Do I pass this point????"+user.getId() + trainerRequest);
        trainer.setSubbject(trainerRequest.getSubject());
        
        trainer.setUser(user);
        
        System.err.println("Trainer"+ trainer);
        trainerDao.save(trainer);
        
    }
    
    @Override
    public void delete(int id) {
        
        trainerDao.delete(id);
    }
    
}
