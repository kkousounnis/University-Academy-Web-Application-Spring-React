package com.spring.boot.coodle.services;

import com.spring.boot.coodle.dao.PasswordResetDao;
import com.spring.boot.coodle.dao.UserDao;
import com.spring.boot.coodle.entities.PasswordResetToken;
import static com.spring.boot.coodle.entities.PasswordResetToken.EXPIRATION;
import com.spring.boot.coodle.entities.User;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
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

    static final String success = "Your password has been successfully reset.";
    static final String tokenExpired = "The token is expired.";
    static final String noToken = "The token does not exists.";

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

        //check if its valid under 24 hours
        List<PasswordResetToken> passwordResetTokens = passwordDao.findAllTokens();
        if (!hasIdPasswordResetToken(passwordResetTokens, user.getId())) {

            //here I create the new token to reset password.
            passwordResetToken = setPasswordResetFields(passwordResetToken, user);
            //save updated entity in the database
            passwordDao.save(passwordResetToken);
        } else {
            //iterate the object password reset token.
            passwordResetToken = iteratePasswordResetToken(passwordResetTokens, user.getId());

            //check if the token is valid. Token is valid only under 24 hours
            if (isTokenExpired(passwordResetToken.getExpiryDate())) {

                passwordResetToken = setPasswordResetFields(passwordResetToken, user);
                passwordDao.update(passwordResetToken.getId(), passwordResetToken);
            }
        }

        return (passwordResetToken.getToken());
    }

    public String resetPassword(String token, String password) {
        //check if its valid under 24 hours
        List<PasswordResetToken> passwordResetTokens = passwordDao.findAllTokens();
        if (null != findByToken(token).getToken()) {
            User user = new User(password);
            PasswordResetToken passwordResetToken = findByToken(token);
            System.err.println(passwordResetToken.getId() + "Token= "
                    + passwordResetToken.getToken());

            //check if the token is valid. Token is valid only under 24 hours
            if (!isTokenExpired(passwordResetToken.getExpiryDate())) {

                //updating in user entity the new password
                userDao.update(passwordResetToken.getUser_id().getId(), user);
                //After I update the password I delete the used token
                passwordDao.delete(passwordResetToken.getId());
                System.err.println(passwordResetToken.getId() + "Token= "
                    + passwordResetToken.getToken());
                return (success);
            } else {
                return (tokenExpired);
            }

        }
        return (noToken);
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
        System.err.println("DIfference" + diff + ">=Expiration: " + EXPIRATION + "=" + (diff.toMinutes() >= EXPIRATION));
        return (diff.toMinutes() >= EXPIRATION);

    }

    public PasswordResetToken setPasswordResetFields(PasswordResetToken passwordResetToken, User user) {
        passwordResetToken.setUser_id(user);
        passwordResetToken.setToken(generateToken());
        passwordResetToken.setExpiryDate(LocalDateTime.now());
//        passwordResetToken.setUser(user);
        return (passwordResetToken);
    }

    /**
     *
     * @param user id of password reset token entity
     * @return true | false if you find the given id.
     */
    public boolean hasIdPasswordResetToken(List<PasswordResetToken> passwordResetTokens, int id) {

        for (PasswordResetToken pResetToken : passwordResetTokens) {
            return (pResetToken.getUser_id().getId() == id);
        }
        return (false);
    }

    /**
     *
     * @param passResetTokens
     * @param id
     * @return object password reset token.
     */
    public PasswordResetToken iteratePasswordResetToken(List<PasswordResetToken> passResetTokens, int id) {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        for (PasswordResetToken passResetToken : passResetTokens) {
            if (passResetToken.getUser_id().getId() == id) {
                passwordResetToken = passResetToken;
            }
        }
        return (passwordResetToken);
    }

    /**
     *
     * @param token
     * @return PasswordResetToken
     */
    public PasswordResetToken findByToken(String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        for (PasswordResetToken passResetToken : passwordDao.findAllTokens()) {
            if (passResetToken.getToken().equals(token)) {
                passwordResetToken = passResetToken;
            }
        }
        return (passwordResetToken);
    }

    public boolean existsByEmail(String username) {
        return (userDao.existsByEmail(username));
    }

}
