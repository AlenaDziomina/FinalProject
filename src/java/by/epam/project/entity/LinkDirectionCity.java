package by.epam.project.entity;

import java.util.Objects;

/**
 * Class of link between direction and city objects.
 * @author Helena.Grouk
 */
public class LinkDirectionCity {
    private Integer idDirection;
    private Integer idCity;
    public LinkDirectionCity(){}
    public LinkDirectionCity(Integer idDirection, Integer idCity) {
        this.idDirection = idDirection;
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
        LinkDirectionCity eq = (LinkDirectionCity)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idDirection, eq.idDirection)) {
            return false;
        }
        if(!Objects.equals(idCity, eq.idCity)) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.idDirection);
        hash = 79 * hash + Objects.hashCode(this.idCity);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idDirection: ");
        str.append(idDirection);
        str.append(", idCity: ");
        str.append(idCity);
        return str.toString();
    }
    public void setIdDirection(Integer idDirection){
        this.idDirection = idDirection;
    }
    public Integer getIdDirection(){
        return this.idDirection;
    }
    public void setIdCity(Integer idCity){
        this.idCity = idCity;
    }
    public Integer getIdCity(){
        return this.idCity;
    }
}
