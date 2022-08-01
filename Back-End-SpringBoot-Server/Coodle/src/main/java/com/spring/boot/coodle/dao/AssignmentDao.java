package com.spring.boot.coodle.dao;

import com.spring.boot.coodle.entities.Assignment;
import java.util.List;

public interface AssignmentDao {

    public List<Assignment> findAllAssignments();

    public Assignment save(Assignment assignment);

    public Assignment update(int id, Assignment assignment);

    public void delete(int id);
}
