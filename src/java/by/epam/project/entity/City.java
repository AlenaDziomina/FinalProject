/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import static by.epam.project.action.ActionCommand.PARAM_NAME_HOTEL_LIST;
import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.query.Criteria;
import java.util.Collection;

/**
 *
 * @author User
 */

public final class City {
    
    private Integer idCity;
    private String name;
    private Short status;
    private String picture;
    private Integer idCountry;
    private Description description;
    private Collection<Hotel> hotelCollection;

    public City() {
    }
    
    public City(Criteria criteria){
        this.setIdCity((Integer) criteria.getParam(PARAM_NAME_ID_CITY));
        this.setName((String) criteria.getParam(PARAM_NAME_NAME_CITY));
        this.setPicture((String) criteria.getParam(PARAM_NAME_PICTURE_CITY));
        this.setStatus((Short) criteria.getParam(PARAM_NAME_STATUS_CITY));
        Description desc = new Description((Integer)criteria.getParam(PARAM_NAME_ID_DESCRIPTION), (String) criteria.getParam(PARAM_NAME_TEXT_DESCRIPTION));
        this.setDescription(desc);
        this.setHotelCollection((Collection<Hotel>) criteria.getParam(PARAM_NAME_HOTEL_LIST));
        this.setIdCountry((Integer) criteria.getParam(PARAM_NAME_ID_COUNTRY));
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer id) {
        this.idCountry = id;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Collection<Hotel> getHotelCollection() {
        return hotelCollection;
    }

    public void setHotelCollection(Collection<Hotel> hotelCollection) {
        this.hotelCollection = hotelCollection;
    }

    
}
