package com.spring.boot.coodle.dao;

import com.spring.boot.coodle.entities.PasswordResetToken;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import com.spring.boot.coodle.repository.PasswordResetTokenRepository;

@Repository
public class PasswordResetDaoImpl implements PasswordResetDao {
    
    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;
    
    @Override
    public List<PasswordResetToken> findAllTokens() {
        return (passwordResetTokenRepository.findAll());
    }
    
    @Override
    public PasswordResetToken save(PasswordResetToken passwordResetToken) {
        return (passwordResetTokenRepository.save(passwordResetToken));
    }
    
    @Override
    public PasswordResetToken update(int id, PasswordResetToken passwordResetToken) {
        
        PasswordResetToken mypasswordResetToken = findById(id);
        //update field with the new token
        mypasswordResetToken.setToken(passwordResetToken.getToken());
        return (passwordResetTokenRepository.save(mypasswordResetToken));
    }
    
    @Override
    public PasswordResetToken findById(int id) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Id not found."));
        
        return (passwordResetToken);
    }
    
    @Override
    public void delete(int id) {
        System.err.println(id+"Do we pass this point to delete!!!");
        passwordResetTokenRepository.deleteById(id);
    }
    
}
