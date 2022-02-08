package com.spring.boot.coodle.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "user_addresses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAddress.findAll", query = "SELECT u FROM UserAddress u"),
    @NamedQuery(name = "UserAddress.findById", query = "SELECT u FROM UserAddress u WHERE u.id = :id"),
    @NamedQuery(name = "UserAddress.findByCountry", query = "SELECT u FROM UserAddress u WHERE u.country = :country"),
    @NamedQuery(name = "UserAddress.findByCity", query = "SELECT u FROM UserAddress u WHERE u.city = :city"),
    @NamedQuery(name = "UserAddress.findByStreetName", query = "SELECT u FROM UserAddress u WHERE u.streetName = :streetName"),
    @NamedQuery(name = "UserAddress.findByStreetNumber", query = "SELECT u FROM UserAddress u WHERE u.streetNumber = :streetNumber"),
    @NamedQuery(name = "UserAddress.findByPostalCode", query = "SELECT u FROM UserAddress u WHERE u.postalCode = :postalCode"),
    @NamedQuery(name = "UserAddress.findByIsShipping", query = "SELECT u FROM UserAddress u WHERE u.isShipping = :isShipping"),
    @NamedQuery(name = "UserAddress.findByIsBilling", query = "SELECT u FROM UserAddress u WHERE u.isBilling = :isBilling")})
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "country", nullable = false, length = 255)
    private String country;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "city", nullable = false, length = 255)
    private String city;
    @Size(max = 255)
    @Column(name = "street_name", length = 255)
    private String streetName;
    @Size(max = 255)
    @Column(name = "street_number", length = 255)
    private String streetNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "postal_code", nullable = false)
    private int postalCode;
    @JsonIgnore
    @Column(name = "is_shipping")
    private Boolean isShipping;
    @JsonIgnore
    @Column(name = "is_billing")
    private Boolean isBilling;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    @ManyToOne(optional = false)
    private User userId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "billingAddressId")
    private List<Orders> ordersList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shippingAddressId")
    private List<Orders> ordersList1;

    public UserAddress() {
    }

    public UserAddress(Integer id) {
        this.id = id;
    }

    public UserAddress(String country, String city, String streetName, String streetNumber, int postalCode, Boolean isShipping, Boolean isBilling, User userId) {
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.isShipping = isShipping;
        this.isBilling = isBilling;
        this.userId = userId;
    }

    public UserAddress(User userId, String country, String city, String streetName, String streetNumber, int postalCode) {
        this.userId = userId;
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
    }

    public UserAddress(Integer id, String country, String city, int postalCode) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public Boolean getIsShipping() {
        return isShipping;
    }

    public void setIsShipping(Boolean isShipping) {
        this.isShipping = isShipping;
    }

    public Boolean getIsBilling() {
        return isBilling;
    }

    public void setIsBilling(Boolean isBilling) {
        this.isBilling = isBilling;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @XmlTransient
    public List<Orders> getOrdersList1() {
        return ordersList1;
    }

    public void setOrdersList1(List<Orders> ordersList1) {
        this.ordersList1 = ordersList1;
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
        if (!(object instanceof UserAddress)) {
            return false;
        }
        UserAddress other = (UserAddress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserAddress{id=").append(id);
        sb.append(", country=").append(country);
        sb.append(", city=").append(city);
        sb.append(", streetName=").append(streetName);
        sb.append(", streetNumber=").append(streetNumber);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", isShipping=").append(isShipping);
        sb.append(", isBilling=").append(isBilling);
        sb.append('}');
        return sb.toString();
    }

}
