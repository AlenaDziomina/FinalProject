package by.epam.project.entity;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

/**
 * Class of country objects.
 * @author Helena.Grouk
 */
public class Country {
    private Integer idCountry;
    private String name;
    private Short status;
    private String picture;
    private Description description;
    private Collection<City> cityCollection;

    public Country() {}
    public Country(Integer idCountry){
        this.idCountry = idCountry;
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
        Country eq = (Country)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idCountry, eq.idCountry)) {
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
        if(description.equals(description)) {
            return false;
        }
        return cityCollection.equals(eq.cityCollection);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idCountry);
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.status);
        hash = 83 * hash + Objects.hashCode(this.picture);
        hash = 83 * hash + Objects.hashCode(this.description);
        hash = 83 * hash + Objects.hashCode(this.cityCollection);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idCountry: ");
        str.append(idCountry);
        str.append(", name: ");
        str.append(name);
        str.append(", status: ");
        str.append(status);
        str.append(", picture: ");
        str.append(picture);
        str.append(", description: ");
        str.append(description);
        str.append(", cityCollection: ");
        str.append(cityCollection);
        return str.toString();
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
    public static class NameComparator implements Comparator<Country> {
        @Override
        public int compare(Country one, Country two) {
            return one.getName().compareTo(two.getName());
        }
    }
}
