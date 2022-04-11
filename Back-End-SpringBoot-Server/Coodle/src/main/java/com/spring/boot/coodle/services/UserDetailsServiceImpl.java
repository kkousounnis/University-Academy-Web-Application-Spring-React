package com.spring.boot.coodle.services;

import com.spring.boot.coodle.entities.PasswordResetToken;
import static com.spring.boot.coodle.entities.PasswordResetToken.EXPIRATION;
import com.spring.boot.coodle.entities.User;
import com.spring.boot.coodle.repository.PasswordResetRepository;
import com.spring.boot.coodle.repository.UserRepository;
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
    UserRepository userRepository;
    
    @Autowired
    PasswordResetRepository passwordResetRepository;

    PasswordResetToken passwordResetToken;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public String forgotPassword(String email) {
        System.err.println("Before Null pointer why.");
        System.err.println(userRepository.findByEmail(email)+"Null pointer why.");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email id."));
        
        user.getResetPasswordToken().setToken(generateToken());
        
        passwordResetToken.setExpiryDate(LocalDateTime.now());

        return (user.getResetPasswordToken()).getToken();
    }

    public String resetPassword(String token, String email) {
        
        PasswordResetToken passwordResetToken = passwordResetRepository.findByToken(token)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid token."));
        User user = userRepository.getById(passwordResetToken.getId());
        
        user.getResetPasswordToken().setToken(generateToken());
        return (user.getResetPasswordToken()).getToken();
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
