package com.spring.boot.coodle.dao;

import com.spring.boot.coodle.entities.PasswordResetToken;

public interface PasswordResetDao {

    public PasswordResetToken save(PasswordResetToken passwordResetToken);

    public PasswordResetToken update(int id, PasswordResetToken passwordResetToken);
    
    public PasswordResetToken findById(int id);

    public PasswordResetToken findByToken(String token);
}
