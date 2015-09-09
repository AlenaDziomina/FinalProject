package by.epam.project.entity;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

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

    public Direction() {}
    public Direction(Integer idDirection) {
        this.idDirection = idDirection;
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
        Direction eq = (Direction)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idDirection, eq.idDirection)) {
            return false;
        }
        if(name == null ? eq.name != null : !name.equals(eq.name)) {
            return false;
        }
        if(text == null ? eq.text != null : !text.equals(eq.text)) {
            return false;
        }
        if(tourType.equals(eq.tourType)) {
            return false;
        }
        if(transMode.equals(eq.transMode)) {
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
        if(countryCollection.equals(eq.countryCollection)) {
            return false;
        }
        if(cityCollection.equals(eq.cityCollection)) {
            return false;
        }
        if(stayCollection.equals(eq.stayCollection)) {
            return false;
        }
        return tourCollection.equals(eq.tourCollection);
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.idDirection);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.text);
        hash = 97 * hash + Objects.hashCode(this.tourType);
        hash = 97 * hash + Objects.hashCode(this.picture);
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + Objects.hashCode(this.transMode);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.countryCollection);
        hash = 97 * hash + Objects.hashCode(this.cityCollection);
        hash = 97 * hash + Objects.hashCode(this.stayCollection);
        hash = 97 * hash + Objects.hashCode(this.tourCollection);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idDirection: ");
        str.append(idDirection);
        str.append(", name: ");
        str.append(name);
        str.append(", text: ");
        str.append(text);
        str.append(", status: ");
        str.append(status);
        str.append(", picture: ");
        str.append(picture);
        str.append(", tourType: ");
        str.append(tourType);
        str.append(", transMode: ");
        str.append(transMode);
        str.append(", description: ");
        str.append(description);
        str.append(", countryCollection: ");
        str.append(countryCollection);
        str.append(", cityCollection: ");
        str.append(cityCollection);
        str.append(", stayCollection: ");
        str.append(stayCollection);
        str.append(", tourCollection: ");
        str.append(tourCollection);
        return str.toString();
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
    public static class NameComparator implements Comparator<Direction> {
        @Override
        public int compare(Direction one, Direction two) {
            return one.getName().compareTo(two.getName());
        }
    }
}
