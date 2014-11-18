package by.epam.project.entity;

import java.util.Collection;

/**
 * Class of city objects.
 * @author Helena.Grouk
 */
public class City {
    
    private Integer idCity;
    private String name;
    private Short status;
    private String picture;
    private Country country;
    private Description description;
    private Collection<Hotel> hotelCollection;

    public City() {
    }
    
    public City(Integer idCity){
        this.idCity = idCity;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
