package by.epam.project.entity;

import java.util.Objects;

/**
 * Class of link between direction and country objects.
 * @author Helena.Grouk
 */
public class LinkDirectionCountry {
    private Integer idDirection;
    private Integer idCountry;

    public LinkDirectionCountry(){}
    public LinkDirectionCountry(Integer idDirection, Integer idCountry) {
        this.idDirection = idDirection;
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
        LinkDirectionCountry eq = (LinkDirectionCountry)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idDirection, eq.idDirection)) {
            return false;
        }
        if(!Objects.equals(idCountry, eq.idCountry)) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.idDirection);
        hash = 79 * hash + Objects.hashCode(this.idCountry);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idDirection: ");
        str.append(idDirection);
        str.append(", idCountry: ");
        str.append(idCountry);
        return str.toString();
    }
    public void setIdDirection(Integer idDirection){
        this.idDirection = idDirection;
    }
    public Integer getIdDirection(){
        return this.idDirection;
    }
    public void setIdCountry(Integer idCountry){
        this.idCountry = idCountry;
    }
    public Integer getIdCountry(){
        return this.idCountry;
    }
}
