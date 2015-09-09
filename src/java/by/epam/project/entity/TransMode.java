package by.epam.project.entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * Class of transportation mode objects.
 * @author Helena.Grouk
 */
public class TransMode implements Serializable {
    private Integer idMode;
    private String nameMode;

    public TransMode() {}
    public TransMode(Integer idMode) {
        this.idMode = idMode;
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
        TransMode eq = (TransMode)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idMode, eq.idMode)) {
            return false;
        }
        return !(nameMode == null ? eq.nameMode != null : !nameMode.equals(eq.nameMode));
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idMode);
        hash = 23 * hash + Objects.hashCode(this.nameMode);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idMode: ");
        str.append(idMode);
        str.append(", nameMode: ");
        str.append(nameMode);
        return str.toString();
    }
    public Integer getIdMode() {
        return idMode;
    }
    public void setIdMode(Integer idMode) {
        this.idMode = idMode;
    }
    public String getNameMode() {
        return nameMode;
    }
    public void setNameMode(String nameMode) {
        this.nameMode = nameMode;
    }
    public static class NameComparator implements Comparator<TransMode> {
        @Override
        public int compare(TransMode one, TransMode two) {
            return one.getNameMode().compareTo(two.getNameMode());
        }
    }
}
