
package com.spring.boot.coodle.repository;

import com.spring.boot.coodle.entities.PasswordResetToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Integer>{
    
    Optional<PasswordResetToken> findByToken(String token);
}
