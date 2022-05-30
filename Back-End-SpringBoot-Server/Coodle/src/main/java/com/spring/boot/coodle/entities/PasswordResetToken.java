package com.spring.boot.coodle.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken implements Serializable {

    public static final int EXPIRATION = 60 * 24;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    private String token;

    private LocalDateTime expiryDate;

    public PasswordResetToken() {
    }

    public PasswordResetToken(Integer id, String token, LocalDateTime expiryDate) {
        this.id = id;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public PasswordResetToken(Integer id, String token, User user, LocalDateTime expiryDate) {
        this.id = id;
        this.token = token;
//        this.user = user;
        this.expiryDate = expiryDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        this.id = Id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PasswordResetToken{");
        sb.append("token=").append(token);
        sb.append('}');
        return sb.toString();
    }

}
