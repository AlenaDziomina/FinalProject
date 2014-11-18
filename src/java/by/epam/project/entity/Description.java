package by.epam.project.entity;

import java.util.Objects;

/**
 * Class of description objects.
 * @author Helena.Grouk
 */
public class Description {
    private Integer idDescription;
    private String text;

    public Description() {}
    public Description(Integer id) {
        this.idDescription = id;
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
        Description eq = (Description)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idDescription, eq.idDescription)) {
            return false;
        }
        return !(text == null ? eq.text != null : !text.equals(eq.text));
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.idDescription);
        hash = 43 * hash + Objects.hashCode(this.text);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idDescription: ");
        str.append(idDescription);
        str.append(", text: ");
        str.append(text);
        return str.toString();
    }
    public Integer getIdDescription() {
        return idDescription;
    }
    public void setIdDescription(Integer idDescription) {
        this.idDescription = idDescription;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
