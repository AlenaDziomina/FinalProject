/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

/**
 *
 * @author User
 */

public class DirectionStayHotels {
    
    private Integer idStay;
    private Integer stayNo;
    private Integer idDirection;
    private Hotel stayHotel;
    private Short status;
    
    public DirectionStayHotels() {
    }
    public DirectionStayHotels(Integer idStay, int stayNo) {
        this.idStay = idStay;
        this.stayNo = stayNo;
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

    public Integer getIdDirection() {
        return idDirection;
    }
    public void setIdDirection(Integer idDirection) {
        this.idDirection = idDirection;
    }
    
    public Hotel getStayHotel(){
        return this.stayHotel;
    }
    public void setStayHotel(Hotel stayHotel){
        this.stayHotel = stayHotel;
    }
    
    public Short getStatus() {
        return status;
    }
    public void setStatus(Short status) {
        this.status = status;
    }

   
}
