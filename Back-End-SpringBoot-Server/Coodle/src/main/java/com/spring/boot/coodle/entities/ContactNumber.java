/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.boot.coodle.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dim.Kasimatis
 */
@Entity
@Table(name = "contact_numbers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContactNumber.findAll", query = "SELECT c FROM ContactNumber c"),
    @NamedQuery(name = "ContactNumber.findById", query = "SELECT c FROM ContactNumber c WHERE c.id = :id"),
    @NamedQuery(name = "ContactNumber.findByTelNumber", query = "SELECT c FROM ContactNumber c WHERE c.telNumber = :telNumber")})
public class ContactNumber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 255)
    @Column(name = "tel_number", length = 255)
    private String telNumber;
    @JoinTable(name = "users_contact_numbers", joinColumns = {
        @JoinColumn(name = "contact_number_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> userList;

    public ContactNumber() {
    }

    public ContactNumber(Integer id) {
        this.id = id;
    }
    
    public ContactNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactNumber)) {
            return false;
        }
        ContactNumber other = (ContactNumber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return telNumber ;
    }
    
}
