package com.spring.boot.coodle.dao;

import com.spring.boot.coodle.entities.Assignment;
import com.spring.boot.coodle.repository.AssignmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AssignmentDaoImpl implements AssignmentDao {

    @Autowired
    AssignmentRepository assignmentRepository;

    @Override
    public List<Assignment> findAllAssignments() {
        return (assignmentRepository.findAll());
    }

    @Override
    public Assignment save(Assignment assignment) {
        return save(assignmentRepository.save(assignment));
    }

    @Override
    public Assignment update(int id, Assignment assignment) {

        Assignment myAssignment = new Assignment();
        /**
         * Update based the new field that you want
         *
         */
        myAssignment = assignment;
        return (assignmentRepository.save(assignment));
    }

    @Override
    public void delete(int id) {
        assignmentRepository.deleteById(id);
    }

}
