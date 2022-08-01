
package com.spring.boot.coodle.services;

import com.spring.boot.coodle.dao.*;
import com.spring.boot.coodle.entities.Assignment;
import java.util.List;

public interface AssignmentService {
    public List<Assignment> findAllAssignments();
    
}
