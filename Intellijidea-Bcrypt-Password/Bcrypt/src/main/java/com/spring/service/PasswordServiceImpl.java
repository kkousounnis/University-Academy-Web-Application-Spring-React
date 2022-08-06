package com.spring.service;

import com.spring.model.Message;
import com.spring.model.Password;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Service
public class PasswordServiceImpl implements PasswordService{

    /**
     * @param password
     * @return Encrypted Password
     */
    public Message encryptPassword(Password password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (null != password) {
            if (null != password.getPassword()) {
                return new Message(passwordEncoder.encode(password.getPassword()));
            }else {
                return new Message( "Error encrypting the new password");
            }
        } else {
            return new Message("Error encrypting the new password");
        }
    }

}
