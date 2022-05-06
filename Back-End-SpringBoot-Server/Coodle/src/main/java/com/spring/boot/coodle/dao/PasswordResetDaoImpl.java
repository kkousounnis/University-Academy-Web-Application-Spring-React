package com.spring.boot.coodle.dao;

import com.spring.boot.coodle.entities.PasswordResetToken;
import com.spring.boot.coodle.repository.PasswordResetRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordResetDaoImpl implements PasswordResetDao {
    
    @Autowired
    PasswordResetRepository passwordResetRepository;
    
    @Override
    public List<PasswordResetToken> findAllTokens() {
        return (passwordResetRepository.findAll());
    }
    
    @Override
    public PasswordResetToken save(PasswordResetToken passwordResetToken) {
        return (passwordResetRepository.save(passwordResetToken));
    }
    
    @Override
    public PasswordResetToken update(int id, PasswordResetToken passwordResetToken) {
        
        PasswordResetToken mypasswordResetToken = findById(id);
        //update field with the new token
        mypasswordResetToken.setToken(passwordResetToken.getToken());
        return (passwordResetRepository.save(mypasswordResetToken));
    }
    
    @Override
    public PasswordResetToken findById(int id) {
        PasswordResetToken passwordResetToken = passwordResetRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Id not found."));
        
        return (passwordResetToken);
    }
    
    @Override
    public void delete(int id) {
        passwordResetRepository.deleteById(id);
    }
    
}
