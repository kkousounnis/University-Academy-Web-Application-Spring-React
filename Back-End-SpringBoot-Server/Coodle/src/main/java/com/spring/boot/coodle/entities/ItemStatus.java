/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.boot.coodle.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dim.Kasimatis
 */
@Entity
@Table(name = "item_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemStatus.findAll", query = "SELECT i FROM ItemStatus i"),
    @NamedQuery(name = "ItemStatus.findById", query = "SELECT i FROM ItemStatus i WHERE i.id = :id"),
    @NamedQuery(name = "ItemStatus.findByStatus", query = "SELECT i FROM ItemStatus i WHERE i.status = :status")})
public class ItemStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "status", nullable = false, length = 255)
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemStatusId", fetch = FetchType.EAGER)
    private List<Orders> ordersList;

    public ItemStatus() {
    }

    public ItemStatus(String status) {
        this.status = status;
    }  

    public ItemStatus(Integer id) {
        this.id = id;
    }

    public ItemStatus(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
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
        if (!(object instanceof ItemStatus)) {
            return false;
        }
        ItemStatus other = (ItemStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.springboot.entities.ItemStatus[ id=" + id + " ]";
    }
    
}
