package by.epam.project.entity;

import java.util.Comparator;
import java.util.Objects;

/**
 * Class of tour type objects.
 * @author Helena.Grouk
 */
public class TourType {
    private Integer idTourType;
    private String nameTourType;

    public TourType() {}
    public TourType(Integer idTourType) {
        this.idTourType = idTourType;
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
        TourType eq = (TourType)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idTourType, eq.idTourType)) {
            return false;
        }
        return !(nameTourType == null ? eq.nameTourType != null : !nameTourType.equals(eq.nameTourType));
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idTourType);
        hash = 23 * hash + Objects.hashCode(this.nameTourType);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idTourType: ");
        str.append(idTourType);
        str.append(", nameTourType: ");
        str.append(nameTourType);
        return str.toString();
    }
    public Integer getIdTourType() {
        return idTourType;
    }
    public void setIdTourType(Integer idTourType) {
        this.idTourType = idTourType;
    }
    public String getNameTourType() {
        return nameTourType;
    }
    public void setNameTourType(String nameTourType) {
        this.nameTourType = nameTourType;
    }
    public static class NameComparator implements Comparator<TourType> {
        @Override
        public int compare(TourType one, TourType two) {
            return one.getNameTourType().compareTo(two.getNameTourType());
        }
    }
}
