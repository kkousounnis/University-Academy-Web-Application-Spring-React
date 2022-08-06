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
    public void update(int id, TrainerRequest trainerRequest) {
        

        trainerDao.update(id, setTrainer(id, trainerRequest, setUser(id, trainerRequest)));
        userDao.updateUserTrainer(setUser(id, trainerRequest));
    }
    
    public User setUser(int id, TrainerRequest trainerRequest) {
        User user = new User();
        user.setId(id);
        user.setPassword(trainerRequest.getPassword());
        user.setEmail(trainerRequest.getEmail());
        user.setFirstName(trainerRequest.getFirstName());
        user.setLastName(trainerRequest.getLastName());
        System.err.println("Check set user"+ user);
        return user;
    }

    public Trainer setTrainer(int id, TrainerRequest trainerRequest, User user) {
        
        Trainer trainer = new Trainer();
        trainer.setId(id);
        trainer.setSubbject(trainerRequest.getSubject());
        trainer.setUser(user);
        return trainer;
    }

    @Override
    public Trainer findById(int id) {

        return (trainerDao.findById(id));
    }

    /**
     *
     * @param trainerRequest
     */
    @Override
    public void save(TrainerRequest trainerRequest) {
        Trainer trainer = new Trainer();
        User user = setUser(trainerRequest);
        Set<Role> roles = setRoles();
        user.setRoles(roles);
        //save user trainer in user table
        user = userDao.save(user);
        trainer.setSubbject(trainerRequest.getSubject());
        trainer.setUser(user);
        //save trainer in table trainer
        trainerDao.save(trainer);

    }

    public Set<Role> setRoles() {
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRole(ERole.ROLE_MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        return roles;
    }

    public User setUser(TrainerRequest trainerRequest) {
        User user = new User();
        user.setEmail(trainerRequest.getEmail());
        user.setFirstName(trainerRequest.getFirstName());
        user.setLastName(trainerRequest.getLastName());
        user.setPassword(trainerRequest.getPassword());
        return user;
    }

    @Override
    public void delete(int id) {

        trainerDao.delete(id);
    }

}
