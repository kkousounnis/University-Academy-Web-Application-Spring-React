/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.boot.coodle.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dim.Kasimatis
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.id = :id"),
    @NamedQuery(name = "Orders.findByComments", query = "SELECT o FROM Orders o WHERE o.comments = :comments"),
    @NamedQuery(name = "Orders.findByTrackingNumber", query = "SELECT o FROM Orders o WHERE o.trackingNumber = :trackingNumber")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 255)
    @Column(name = "comments", length = 255)
    private String comments;
    @Size(max = 255)
    @Column(name = "tracking_number", length = 255)
    private String trackingNumber;
    @JoinColumn(name = "item_status_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ItemStatus itemStatusId;
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Product productId;
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private UserAddress billingAddressId;
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private UserAddress shippingAddressId;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;

    public Orders() {
    }

    public Orders(String comments, String trackingNumber, ItemStatus itemStatusId, Product productId, UserAddress billingAddressId, UserAddress shippingAddressId, User userId) {
        this.comments = comments;
        this.trackingNumber = trackingNumber;
        this.itemStatusId = itemStatusId;
        this.productId = productId;
        this.billingAddressId = billingAddressId;
        this.shippingAddressId = shippingAddressId;
        this.userId = userId;
    }

    public Orders(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public ItemStatus getItemStatusId() {
        return itemStatusId;
    }

    public void setItemStatusId(ItemStatus itemStatusId) {
        this.itemStatusId = itemStatusId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public UserAddress getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(UserAddress billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public UserAddress getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(UserAddress shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orders{id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    

}
