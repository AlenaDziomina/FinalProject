/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

import java.util.Collection;

/**
 * Class of direction objects.
 * @author Helena.Grouk
 */

public class Direction {
    
    private Integer idDirection;
    private String name;
    private String text;
    private TourType tourType;
    private String picture;
    private Short status;
    private TransMode transMode;
    private Description description;
    private Collection<Country> countryCollection;
    private Collection<City> cityCollection;
    private Collection<DirectionStayHotel> stayCollection;
    private Collection<Tour> tourCollection;
    
   
    public Direction() {
    }
    public Direction(Integer idDirection) {
        this.idDirection = idDirection;
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
    
    public TransMode getTransMode() {
        return transMode;
    }
    public void setTransMode(TransMode transMode) {
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
    public void setCountryCollection(Collection<Country> countryCollection) {
        this.countryCollection = countryCollection;
    }
    
    public Collection<City> getCityCollection() {
        return cityCollection;
    }
    public void setCityCollection(Collection<City> cityCollection) {
        this.cityCollection = cityCollection;
    }
    
    public Collection<DirectionStayHotel> getStayCollection() {
        return stayCollection;
    }
    public void setStayCollection(Collection<DirectionStayHotel> stayCollection) {
        this.stayCollection = stayCollection;
    }

    public Collection<Tour> getTourCollection() {
        return tourCollection;
    }
    public void setTourCollection(Collection<Tour> tourCollection) {
        this.tourCollection = tourCollection;
    }

}
