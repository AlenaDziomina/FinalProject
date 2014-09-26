/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import static by.epam.project.action.ActionCommand.PARAM_NAME_CITY_LIST;
import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.query.Criteria;
import java.util.Collection;

/**
 *
 * @author User
 */

public final class Country {
    
    private Integer idCountry;
    private String name;
    private Short status;
    private String picture;
    private Description description;
    private Collection<City> cityCollection;

    public Country() {
    }

    public Country(Criteria criteria) {
        this.setIdCountry((Integer) criteria.getParam(PARAM_NAME_ID_COUNTRY));
        this.setName((String) criteria.getParam(PARAM_NAME_NAME_COUNTRY));
        this.setPicture((String) criteria.getParam(PARAM_NAME_PICTURE_COUNTRY));
        this.setStatus((Short) criteria.getParam(PARAM_NAME_STATUS_COUNTRY));
        Description desc = new Description((Integer)criteria.getParam(PARAM_NAME_ID_DESCRIPTION), (String) criteria.getParam(PARAM_NAME_TEXT_DESCRIPTION));
        this.setDescription(desc);
        this.setCityCollection((Collection<City>) criteria.getParam(PARAM_NAME_CITY_LIST));
    }

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
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

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Collection<City> getCityCollection() {
        return cityCollection;
    }

    public void setCityCollection(Collection<City> cityCollection) {
        this.cityCollection = cityCollection;
    }

    
    
}
