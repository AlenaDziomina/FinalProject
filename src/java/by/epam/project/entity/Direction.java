/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import static by.epam.project.action.ActionCommand.PARAM_NAME_CITY_LIST;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_COUNTRY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_DESCRIPTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_DIRECTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_MODE;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_TOUR_TYPE;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_NAME_COUNTRY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_NAME_DIRECTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_NAME_MODE;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_NAME_TOUR_TYPE;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_PICTURE_COUNTRY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_PICTURE_DIRECTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_STATUS_COUNTRY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_STATUS_DIRECTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_TEXT_DESCRIPTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_TEXT_DIRECTION;
import by.epam.project.dao.query.Criteria;
import java.util.Collection;

/**
 *
 * @author User
 */

public final class Direction {
    
    private Integer idDirection;
    private String name;
    private TourType tourType;
    private TransportationMode transMode;
    private String text;
    private Description description;
    private String picture;
    private Short status;
    private Collection<Country> countryCollection;
    private Collection<City> cityCollection;
    private Collection<DirectionStayHotels> stayCollection;
    private Collection<Tour> tourCollection;
    
   
    public Direction() {
    }
    public Direction(Integer idDirection) {
        this.idDirection = idDirection;
    }
    public Direction(Criteria criteria) {
        this.setIdDirection((Integer) criteria.getParam(PARAM_NAME_ID_DIRECTION));
        this.setName((String) criteria.getParam(PARAM_NAME_NAME_DIRECTION));
        this.setPicture((String) criteria.getParam(PARAM_NAME_PICTURE_DIRECTION));
        this.setStatus((Short) criteria.getParam(PARAM_NAME_STATUS_DIRECTION));
        this.setText((String) criteria.getParam(PARAM_NAME_TEXT_DIRECTION));
        Description desc = new Description((Integer)criteria.getParam(PARAM_NAME_ID_DESCRIPTION), (String) criteria.getParam(PARAM_NAME_TEXT_DESCRIPTION));
        this.setDescription(desc);
        TourType type = new TourType((Integer)criteria.getParam(PARAM_NAME_ID_TOUR_TYPE), (String) criteria.getParam(PARAM_NAME_NAME_TOUR_TYPE));
        this.setTourType(type);
        TransportationMode mode = new TransportationMode((Integer)criteria.getParam(PARAM_NAME_ID_MODE), (String) criteria.getParam(PARAM_NAME_NAME_MODE));
        this.setTransMode(mode);
        
    }

    public Integer getIdDirection() {
        return idDirection;
    }
    public void setIdDirection(Integer idDirection) {
        this.idDirection = idDirection;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public TourType getTourType() {
        return tourType;
    }
    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }
    
    public TransportationMode getTransMode() {
        return transMode;
    }
    public void setTransMode(TransportationMode transMode) {
        this.transMode = transMode;
    }
    
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    
    public Description getDescription() {
        return description;
    }
    public void setDescription(Description description) {
        this.description = description;
    }
    
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Short getStatus() {
        return status;
    }
    public void setStatus(Short status) {
        this.status = status;
    }

    public Collection<Country> getCountryCollection() {
        return countryCollection;
    }
    public void setCountryCollection(Collection<Country> tourCollection) {
        this.countryCollection = countryCollection;
    }
    
    public Collection<City> getCityCollection() {
        return cityCollection;
    }
    public void setCityCollection(Collection<City> cityCollection) {
        this.cityCollection = cityCollection;
    }
    
    public Collection<DirectionStayHotels> getStayCollection() {
        return stayCollection;
    }
    public void setStayCollection(Collection<DirectionStayHotels> stayCollection) {
        this.stayCollection = stayCollection;
    }

    public Collection<Tour> getTourCollection() {
        return tourCollection;
    }
    public void setTourCollection(Collection<Tour> tourCollection) {
        this.tourCollection = tourCollection;
    }

}
