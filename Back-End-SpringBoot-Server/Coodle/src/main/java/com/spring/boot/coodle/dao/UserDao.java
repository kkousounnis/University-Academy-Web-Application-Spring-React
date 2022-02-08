package com.spring.boot.coodle.dao;

import com.spring.boot.coodle.entities.User;
import java.util.List;

public interface UserDao {

    public List<User> findAllUsers();

    public User findById(int id);

    public User save(User user);

    public void delete(int id);

    public User update(int id, User user);
}
