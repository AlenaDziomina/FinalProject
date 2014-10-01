/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.query.Criteria;

/**
 *
 * @author User
 */

public final class Hotel {
    
    private Integer idHotel;
    private String name;
    private Integer stars;
    private Short status;
    private String picture;
    private Integer idCity;
    private Description description;

    public Hotel() {
    }
    
    public Hotel(Integer idHotel) {
        setIdHotel(idHotel);
    }
    
    public Hotel(Criteria criteria) {
        this.setIdHotel((Integer) criteria.getParam(PARAM_NAME_ID_HOTEL));
        this.setName((String) criteria.getParam(PARAM_NAME_NAME_HOTEL));
        this.setPicture((String) criteria.getParam(PARAM_NAME_PICTURE_HOTEL));
        this.setStatus((Short) criteria.getParam(PARAM_NAME_STATUS_HOTEL));
        this.setStars((Integer) criteria.getParam(PARAM_NAME_STARS_HOTEL));
        Description desc = new Description((Integer)criteria.getParam(PARAM_NAME_ID_DESCRIPTION), (String) criteria.getParam(PARAM_NAME_TEXT_DESCRIPTION));
        this.setDescription(desc);
        this.setIdCity((Integer) criteria.getParam(PARAM_NAME_ID_CITY));
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

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    
}
