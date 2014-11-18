package by.epam.project.entity;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

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

    public City() {}
    public City(Integer idCity){
        this.idCity = idCity;
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
        City eq = (City)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idCity, eq.idCity)) {
            return false;
        }
        if(name == null ? eq.name != null : !name.equals(eq.name)) {
            return false;
        }
        if(!Objects.equals(status, eq.status)) {
            return false;
        }
        if(picture == null ? eq.picture != null : !picture.equals(eq.picture)) {
            return false;
        }
        if(country.equals(eq.country)) {
            return false;
        }
        if(description.equals(description)) {
            return false;
        }
        return hotelCollection.equals(eq.hotelCollection);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idCity);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.status);
        hash = 53 * hash + Objects.hashCode(this.picture);
        hash = 53 * hash + Objects.hashCode(this.country);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Objects.hashCode(this.hotelCollection);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idCity: ");
        str.append(idCity);
        str.append(", name: ");
        str.append(name);
        str.append(", status: ");
        str.append(status);
        str.append(", picture: ");
        str.append(picture);
        str.append(", country: ");
        str.append(country);
        str.append(", description: ");
        str.append(description);
        str.append(", hotelCollection: ");
        str.append(hotelCollection);
        return str.toString();
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
    public static class NameComparator implements Comparator<City> {
        @Override
        public int compare(City one, City two) {
            return one.getName().compareTo(two.getName());
        }
    }
}
