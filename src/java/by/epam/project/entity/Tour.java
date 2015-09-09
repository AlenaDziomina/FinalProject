package by.epam.project.entity;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 * Class of tour objects.
 * @author Helena.Grouk
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

    public Tour() {}
    public Tour(Integer idTour) {
        this.idTour = idTour;
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
        Tour eq = (Tour)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idTour, eq.idTour)) {
            return false;
        }
        if(!Objects.equals(departDate, eq.departDate)) {
            return false;
        }
        if(!Objects.equals(daysCount, eq.daysCount)) {
            return false;
        }
        if(!Objects.equals(price, eq.price)) {
            return false;
        }
        if(!Objects.equals(discount, eq.discount)) {
            return false;
        }
        if(!Objects.equals(totalSeats, eq.totalSeats)) {
            return false;
        }
        if(!Objects.equals(freeSeats, eq.freeSeats)) {
            return false;
        }
        if(direction.equals(direction)) {
            return false;
        }
        if(!Objects.equals(status, eq.status)) {
            return false;
        }
        return orderCollection.equals(eq.orderCollection);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idTour);
        hash = 79 * hash + Objects.hashCode(this.departDate);
        hash = 79 * hash + Objects.hashCode(this.daysCount);
        hash = 79 * hash + Objects.hashCode(this.price);
        hash = 79 * hash + Objects.hashCode(this.discount);
        hash = 79 * hash + Objects.hashCode(this.totalSeats);
        hash = 79 * hash + Objects.hashCode(this.freeSeats);
        hash = 79 * hash + Objects.hashCode(this.direction);
        hash = 79 * hash + Objects.hashCode(this.status);
        hash = 79 * hash + Objects.hashCode(this.orderCollection);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idTour: ");
        str.append(idTour);
        str.append(", departDate: ");
        str.append(departDate);
        str.append(", daysCount: ");
        str.append(daysCount);
        str.append(", price: ");
        str.append(price);
        str.append(", discount: ");
        str.append(discount);
        str.append(", totalSeats: ");
        str.append(totalSeats);
        str.append(", freeSeats: ");
        str.append(freeSeats);
        str.append(", direction: ");
        str.append(direction);
        str.append(", status: ");
        str.append(status);
        str.append(", orderCollection: ");
        str.append(orderCollection);
        return str.toString();
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
