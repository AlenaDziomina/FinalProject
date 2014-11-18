/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import java.util.Comparator;

/**
 *
 * @author User
 */

public class DirectionStayHotel {

    private Integer idStay;
    private Integer stayNo;
    private Direction direction;
    private Hotel hotel;
    private Short status;

    public DirectionStayHotel() {
    }
    public DirectionStayHotel(Integer idStay) {
        this.idStay = idStay;
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
