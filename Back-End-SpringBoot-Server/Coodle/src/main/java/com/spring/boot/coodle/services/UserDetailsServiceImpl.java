package com.spring.boot.coodle.services;

import com.spring.boot.coodle.dao.PasswordResetDao;
import com.spring.boot.coodle.dao.UserDao;
import com.spring.boot.coodle.entities.PasswordResetToken;
import static com.spring.boot.coodle.entities.PasswordResetToken.EXPIRATION;
import com.spring.boot.coodle.entities.User;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PasswordResetDao passwordDao;

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);
        return UserDetailsImpl.build(user);
    }

    /**
     * Forgot password method.
     *
     * @param email
     * @return password reset token
     */
    public String forgotPassword(String email) {

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        User user = userDao.findByEmail(email);
        passwordResetToken.setUser_id(user.getId());
        passwordResetToken.setToken(generateToken());
        passwordResetToken.setExpiryDate(LocalDateTime.now());
        passwordResetToken.setUser(user);

        //save updated entity in the database
        passwordDao.save(passwordResetToken);
        return (passwordResetToken.getToken());
    }

    public String resetPassword(String token, String email) {

        PasswordResetToken passwordResetToken = passwordDao.findByToken(token);
        User user = userDao.findById(passwordResetToken.getId());
        //To be implemented seconed step
        //user.getResetPasswordToken().setToken(generateToken());
        //return (user.getResetPasswordToken()).getToken();
        return ("");
    }

    /**
     * Generate unique token. You may add multiple parameters to create a strong
     * token.
     *
     * @return unique token
     */
    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

    /**
     * Check whether the created token expired or not.
     *
     * @param tokenCreationDate
     * @return true or false
     */
    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRATION;

    }
}
