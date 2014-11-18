package by.epam.project.entity;

import java.util.Comparator;
import java.util.Objects;

/**
 * Class of direction stay hotel objects.
 * @author Helena.Grouk
 */
public class DirectionStayHotel {
    private Integer idStay;
    private Integer stayNo;
    private Direction direction;
    private Hotel hotel;
    private Short status;

    public DirectionStayHotel() {}
    public DirectionStayHotel(Integer idStay) {
        this.idStay = idStay;
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
        DirectionStayHotel eq = (DirectionStayHotel)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idStay, eq.idStay)) {
            return false;
        }
        if(!Objects.equals(stayNo, eq.stayNo)) {
            return false;
        }
        if(direction.equals(eq.direction)) {
            return false;
        }
        if(hotel.equals(eq.hotel)) {
            return false;
        }
        return Objects.equals(status, eq.status);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idStay);
        hash = 41 * hash + Objects.hashCode(this.stayNo);
        hash = 41 * hash + Objects.hashCode(this.direction);
        hash = 41 * hash + Objects.hashCode(this.hotel);
        hash = 41 * hash + Objects.hashCode(this.status);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idStay: ");
        str.append(idStay);
        str.append(", stayNo: ");
        str.append(stayNo);
        str.append(", direction: ");
        str.append(direction);
        str.append(", status: ");
        str.append(status);
        str.append(", hotel: ");
        str.append(hotel);
        return str.toString();
    }
    public DirectionStayHotel(Integer idDirection, Integer idHotel, Integer stayNo) {
        this.stayNo = stayNo;
        this.direction = new Direction(idDirection);
        this.hotel = new Hotel(idHotel);
    }
    public Integer getIdStay() {
        return idStay;
    }
    public void setIdStay(Integer idStay) {
        this.idStay = idStay;
    }
    public Integer getStayNo() {
        return stayNo;
    }
    public void setStayNo(Integer stayNo) {
        this.stayNo = stayNo;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public Hotel getHotel(){
        return this.hotel;
    }
    public void setHotel(Hotel hotel){
        this.hotel = hotel;
    }
    public Short getStatus() {
        return status;
    }
    public void setStatus(Short status) {
        this.status = status;
    }
    public static class NameHotelComparator implements Comparator<DirectionStayHotel> {
        @Override
        public int compare(DirectionStayHotel one, DirectionStayHotel two) {
            return one.getHotel().getName().compareTo(two.getHotel().getName());
        }
    }
}
