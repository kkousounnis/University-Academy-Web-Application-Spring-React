package com.spring.boot.coodle.dao;

import com.spring.boot.coodle.entities.User;
import com.spring.boot.coodle.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return (userRepository.findAll());
    }

    @Override
    public User findById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Id not found."));
        return (user);
    }

    @Override
    public User findByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username:" + email));
        return (user);
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
        //Update user with the new password.
        myUser.setPassword(user.getPassword());
        return userRepository.save(myUser);
    }

}
