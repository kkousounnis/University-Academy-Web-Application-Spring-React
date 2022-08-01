package com.spring.service;

import com.spring.model.Message;
import com.spring.model.Password;

public interface PasswordService {

    public Message encryptPassword(Password password);
}
