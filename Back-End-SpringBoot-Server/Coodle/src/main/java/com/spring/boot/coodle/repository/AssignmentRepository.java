
package com.spring.boot.coodle.repository;

import com.spring.boot.coodle.entities.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer>{
    
}
