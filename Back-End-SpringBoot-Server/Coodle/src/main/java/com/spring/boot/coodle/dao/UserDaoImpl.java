package com.spring.boot.coodle.dao;

import com.spring.boot.coodle.entities.User;
import com.spring.boot.coodle.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return (userRepository.findAll());
    }

    @Override
    public User findById(int id) {
        User user = userRepository.findById(id).get();
        if (user == null) {
            return null;
        }
        return (userRepository.findById(id).get());
    }

    @Override
    public User save(User user) {
        return (userRepository.save(user));
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(int id, User user) {
        User myUser = userRepository.findById(id).get();
        myUser = user;
        return userRepository.save(myUser);
    }

}
