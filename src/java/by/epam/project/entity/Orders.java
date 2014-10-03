/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByIdOrder", query = "SELECT o FROM Orders o WHERE o.idOrder = :idOrder"),
    @NamedQuery(name = "Orders.findBySeats", query = "SELECT o FROM Orders o WHERE o.seats = :seats"),
    @NamedQuery(name = "Orders.findByCurrentPrice", query = "SELECT o FROM Orders o WHERE o.currentPrice = :currentPrice"),
    @NamedQuery(name = "Orders.findByCurrentDiscount", query = "SELECT o FROM Orders o WHERE o.currentDiscount = :currentDiscount"),
    @NamedQuery(name = "Orders.findByCurrentUserDiscount", query = "SELECT o FROM Orders o WHERE o.currentUserDiscount = :currentUserDiscount"),
    @NamedQuery(name = "Orders.findByFinalPrice", query = "SELECT o FROM Orders o WHERE o.finalPrice = :finalPrice"),
    @NamedQuery(name = "Orders.findByOrderDate", query = "SELECT o FROM Orders o WHERE o.orderDate = :orderDate")})
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_order")
    private Integer idOrder;
    @Basic(optional = false)
    @Column(name = "seats")
    private int seats;
    @Basic(optional = false)
    @Column(name = "current_price")
    private float currentPrice;
    @Basic(optional = false)
    @Column(name = "current_discount")
    private int currentDiscount;
    @Basic(optional = false)
    @Column(name = "current_user_discount")
    private int currentUserDiscount;
    @Basic(optional = false)
    @Column(name = "final_price")
    private int finalPrice;
    @Column(name = "orderDate")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrder")
    private Collection<Tourist> touristCollection;

    public Orders() {
    }

    public Orders(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Orders(Integer idOrder, int seats, float currentPrice, int currentDiscount, int currentUserDiscount, int finalPrice) {
        this.idOrder = idOrder;
        this.seats = seats;
        this.currentPrice = currentPrice;
        this.currentDiscount = currentDiscount;
        this.currentUserDiscount = currentUserDiscount;
        this.finalPrice = finalPrice;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getCurrentDiscount() {
        return currentDiscount;
    }

    public void setCurrentDiscount(int currentDiscount) {
        this.currentDiscount = currentDiscount;
    }

    public int getCurrentUserDiscount() {
        return currentUserDiscount;
    }

    public void setCurrentUserDiscount(int currentUserDiscount) {
        this.currentUserDiscount = currentUserDiscount;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @XmlTransient
    public Collection<Tourist> getTouristCollection() {
        return touristCollection;
    }

    public void setTouristCollection(Collection<Tourist> touristCollection) {
        this.touristCollection = touristCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "by.epam.project.entity.Orders[ idOrder=" + idOrder + " ]";
    }
    
}
