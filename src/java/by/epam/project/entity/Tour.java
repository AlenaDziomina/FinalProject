/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author User
 */

public class Tour {

    private Integer idTour;
    private Date departDate;
    private Integer daysCount;
    private Float price;
    private Integer discount;
    private Integer totalSeats;
    private Integer freeSeats;
    private Direction direction;
    private Short status;
    private Collection<Order> orderCollection;

    public Tour() {
    }
    public Tour(Integer idTour) {
        this.idTour = idTour;
    }

    public Integer getIdTour() {
        return idTour;
    }
    public void setIdTour(Integer idTour) {
        this.idTour = idTour;
    }

    public Date getDepartDate() {
        return departDate;
    }
    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    public Integer getDaysCount() {
        return daysCount;
    }
    public void setDaysCount(Integer daysCount) {
        this.daysCount = daysCount;
    }

    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }
    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getFreeSeats() {
        return freeSeats;
    }
    public void setFreeSeats(Integer freeSeats) {
        this.freeSeats = freeSeats;
    }

    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Short getStatus() {
        return status;
    }
    public void setStatus(Short status) {
        this.status = status;
    }

    public Collection<Order> getOrderCollection() {
        return orderCollection;
    }
    public void setOrderCollection(Collection<Order> orderCollection) {
        this.orderCollection = orderCollection;
    }

    public static class DateComparator implements Comparator<Tour> {
        @Override
        public int compare(Tour one, Tour two) {
            return one.getDepartDate().compareTo(two.getDepartDate());
        }
    }

}
