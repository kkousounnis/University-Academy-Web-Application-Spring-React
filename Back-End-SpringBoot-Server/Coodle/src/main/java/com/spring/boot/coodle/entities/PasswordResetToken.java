
package com.spring.boot.coodle.entities;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "passwordresettokens")
public class PasswordResetToken {

    public static final int EXPIRATION = 60 * 24;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer Id;

    private String token;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime expiryDate;

    public PasswordResetToken() {
    }

    public PasswordResetToken(Integer Id, String token, LocalDateTime expiryDate) {
        this.Id = Id;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public PasswordResetToken(Integer Id, String token, User user, LocalDateTime expiryDate) {
        this.Id = Id;
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }
    
    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PasswordResetToken{");
        sb.append("token=").append(token);
        sb.append('}');
        return sb.toString();
    }

}
