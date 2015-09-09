package by.epam.project.entity;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 * Class of order objects.
 * @author Helena.Grouk
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
    private Short status;
    private Collection<Tourist> touristCollection;

    public Order() {}
    public Order(Integer idOrder) {
        this.idOrder = idOrder;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Order eq = (Order)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idOrder, eq.idOrder)) {
            return false;
        }
        if(tour.equals(eq.tour)) {
            return false;
        }
        if(user.equals(eq.user)) {
            return false;
        }
        if(!Objects.equals(seats, eq.seats)) {
            return false;
        }
        if(!Objects.equals(currentPrice, eq.currentPrice)) {
            return false;
        }
        if(!Objects.equals(currentDiscount, eq.currentDiscount)) {
            return false;
        }
        if(!Objects.equals(currentUserDiscount, eq.currentUserDiscount)) {
            return false;
        }
        if(!Objects.equals(finalPrice, eq.finalPrice)) {
            return false;
        }
        if(!Objects.equals(orderDate, eq.orderDate)) {
            return false;
        }
        if(!Objects.equals(status, eq.status)) {
            return false;
        }
        return touristCollection.equals(eq.touristCollection);
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idOrder);
        hash = 71 * hash + Objects.hashCode(this.tour);
        hash = 71 * hash + Objects.hashCode(this.user);
        hash = 71 * hash + Objects.hashCode(this.seats);
        hash = 71 * hash + Objects.hashCode(this.currentPrice);
        hash = 71 * hash + Objects.hashCode(this.currentDiscount);
        hash = 71 * hash + Objects.hashCode(this.currentUserDiscount);
        hash = 71 * hash + Objects.hashCode(this.finalPrice);
        hash = 71 * hash + Objects.hashCode(this.orderDate);
        hash = 71 * hash + Objects.hashCode(this.status);
        hash = 71 * hash + Objects.hashCode(this.touristCollection);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idOrder: ");
        str.append(idOrder);
        str.append(", tour: ");
        str.append(tour);
        str.append(", user: ");
        str.append(user);
        str.append(", seats: ");
        str.append(seats);
        str.append(", currentPrice: ");
        str.append(currentPrice);
        str.append(", currentDiscount: ");
        str.append(currentDiscount);
        str.append(", currentUserDiscount: ");
        str.append(currentUserDiscount);
        str.append(", finalPrice: ");
        str.append(finalPrice);
        str.append(", orderDate: ");
        str.append(orderDate);
        str.append(", status: ");
        str.append(status);
        str.append(", touristCollection: ");
        str.append(touristCollection);
        return str.toString();
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
    public Short getStatus() {
        return status;
    }
    public void setStatus(Short status) {
        this.status = status;
    }
    public static class DateComparator implements Comparator<Order> {
        @Override
        public int compare(Order one, Order two) {
            return two.getOrderDate().compareTo(one.getOrderDate());
        }
    }
}
