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

public final class Hotel {
    
    public static final String DB_HOTEL = "hotel";
    public static final String DB_HOTEL_ID_HOTEL = "id_hotel";
    public static final String DB_HOTEL_ID_CITY = "id_city";
    public static final String DB_HOTEL_NAME = "name";
    public static final String DB_HOTEL_STARS = "stars";
    public static final String DB_HOTEL_STATUS = "status";
    public static final String DB_HOTEL_PICTURE = "picture";
    public static final String DB_HOTEL_ID_DESCRIPTION = "id_description";
    
    private Integer idHotel;
    private String name;
    private Integer stars;
    private Short status;
    private String picture;
    private City city;
    private Description description;

    public Hotel() {
    }
    
    public Hotel(Integer idHotel) {
        setIdHotel(idHotel);
    }

    public Integer getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel) {
        this.idHotel = idHotel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    
}
