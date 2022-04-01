
package com.spring.boot.coodle.entities;

import java.io.Serializable;
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
@Table(name = "students")
public class Student implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "tuition_fees", nullable = false)
    private long tuitionFees; 
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public Student() {
    }

    public Student(Integer id, long tuitionFees, User user) {
        this.id = id;
        this.tuitionFees = tuitionFees;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(long tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student{");
        sb.append("id=").append(id);
        sb.append(", tuitionFees=").append(tuitionFees);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
