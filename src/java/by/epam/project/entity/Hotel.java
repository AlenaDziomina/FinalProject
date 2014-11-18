package by.epam.project.entity;

import java.util.Comparator;
import java.util.Objects;

/**
 * Class of hotel objects.
 * @author Helena.Grouk
 */
public class Hotel {
    private Integer idHotel;
    private String name;
    private Integer stars;
    private Short status;
    private String picture;
    private City city;
    private Description description;

    public Hotel() {}
    public Hotel(Integer idHotel) {
        this.idHotel = idHotel;
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
        Hotel eq = (Hotel)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idHotel, eq.idHotel)) {
            return false;
        }
        if(name == null ? eq.name != null : !name.equals(eq.name)) {
            return false;
        }
        if(!Objects.equals(stars, eq.stars)) {
            return false;
        }
        if(!Objects.equals(status, eq.status)) {
            return false;
        }
        if(picture == null ? eq.picture != null : !picture.equals(eq.picture)) {
            return false;
        }
        if(city.equals(eq.city)) {
            return false;
        }
        return !description.equals(description);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idHotel);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.stars);
        hash = 89 * hash + Objects.hashCode(this.status);
        hash = 89 * hash + Objects.hashCode(this.picture);
        hash = 89 * hash + Objects.hashCode(this.city);
        hash = 89 * hash + Objects.hashCode(this.description);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idHotel: ");
        str.append(idHotel);
        str.append(", name: ");
        str.append(name);
        str.append(", stars: ");
        str.append(stars);
        str.append(", status: ");
        str.append(status);
        str.append(", picture: ");
        str.append(picture);
        str.append(", city: ");
        str.append(city);
        str.append(", description: ");
        str.append(description);
        return str.toString();
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
    public static class NameComparator implements Comparator<Hotel> {
        @Override
        public int compare(Hotel one, Hotel two) {
            return one.getName().compareTo(two.getName());
        }
    }
    public static class StarsComparator implements Comparator<Hotel> {
        @Override
        public int compare(Hotel one, Hotel two) {
            return two.getStars().compareTo(one.getStars());
        }
    }
}
