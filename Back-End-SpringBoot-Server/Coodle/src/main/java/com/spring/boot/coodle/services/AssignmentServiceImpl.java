package com.spring.boot.coodle.services;

import com.spring.boot.coodle.dao.*;
import com.spring.boot.coodle.entities.Assignment;
import com.spring.boot.coodle.repository.AssignmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    AssignmentDao assignmentDao;

    @Override
    public List<Assignment> findAllAssignments() {
        return (assignmentDao.findAllAssignments());
    }

}
