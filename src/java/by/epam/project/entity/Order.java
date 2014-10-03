/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author User
 */
public class Order {
    
    private Integer idOrder;
    private Tour tour;
    private User user;
    private Integer seats;
    private Float currentPrice;
    private Integer currentDiscount;
    private Integer currentUserDiscount;
    private Float finalPrice;
    private Date orderDate;
    private Collection<Tourist> touristCollection;

    public Order() {
    }

    public Order(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdOrder() {
        return idOrder;
    }
    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Tour getTour(){
        return this.tour;
    }
    public void setTour(Tour tour){
        this.tour = tour;
    }
    
    public User getUser(){
        return this.user;
    }
    public void setUser(User user){
        this.user = user;
    }
    
    public Integer getSeats() {
        return seats;
    }
    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Float getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(Float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Integer getCurrentDiscount() {
        return currentDiscount;
    }
    public void setCurrentDiscount(Integer currentDiscount) {
        this.currentDiscount = currentDiscount;
    }

    public Integer getCurrentUserDiscount() {
        return currentUserDiscount;
    }
    public void setCurrentUserDiscount(Integer currentUserDiscount) {
        this.currentUserDiscount = currentUserDiscount;
    }

    public Float getFinalPrice() {
        return finalPrice;
    }
    public void setFinalPrice(Float finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Collection<Tourist> getTouristCollection() {
        return touristCollection;
    }
    public void setTouristCollection(Collection<Tourist> touristCollection) {
        this.touristCollection = touristCollection;
    }

}
