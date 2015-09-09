package by.epam.project.entity;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 * Class of tourist objects.
 * @author Helena.Grouk
 */
public class Tourist {
    private Integer idTourist;
    private Order order;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthDate;
    private String passport;
    private Short status;

    public Tourist() {}
    public Tourist(Integer idTourist) {
        this.idTourist = idTourist;
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
        Tourist eq = (Tourist)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idTourist, eq.idTourist)) {
            return false;
        }
        if(order.equals(eq.order)) {
            return false;
        }
        if(firstName == null ? eq.firstName != null : !firstName.equals(eq.firstName)) {
            return false;
        }
        if(middleName == null ? eq.middleName != null : !middleName.equals(eq.middleName)) {
            return false;
        }
        if(lastName == null ? eq.lastName != null : !lastName.equals(eq.lastName)) {
            return false;
        }
        if(passport == null ? eq.passport != null : !passport.equals(eq.passport)) {
            return false;
        }
        return Objects.equals(status, eq.status);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idTourist);
        hash = 37 * hash + Objects.hashCode(this.order);
        hash = 37 * hash + Objects.hashCode(this.firstName);
        hash = 37 * hash + Objects.hashCode(this.middleName);
        hash = 37 * hash + Objects.hashCode(this.lastName);
        hash = 37 * hash + Objects.hashCode(this.passport);
        hash = 37 * hash + Objects.hashCode(this.status);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idTourist: ");
        str.append(idTourist);
        str.append(", order: ");
        str.append(order);
        str.append(", firstName: ");
        str.append(firstName);
        str.append(", middleName: ");
        str.append(middleName);
        str.append(", lastName: ");
        str.append(lastName);
        str.append(", passport: ");
        str.append(passport);
        str.append(", status: ");
        str.append(status);
        return str.toString();
    }
    public Integer getIdTourist() {
        return idTourist;
    }
    public void setIdTourist(Integer idTourist) {
        this.idTourist = idTourist;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public String getPassport() {
        return passport;
    }
    public void setPassport(String passport) {
        this.passport = passport;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Short getStatus() {
        return status;
    }
    public void setStatus(Short status) {
        this.status = status;
    }
    public static class FioComparator implements Comparator<Tourist> {
        @Override
        public int compare(Tourist one, Tourist two) {
            int first = one.getLastName().compareTo(two.getLastName());
            if (first == 0) {
                int second = one.getFirstName().compareTo(two.getFirstName());
                if (second == 0) {
                    return one.getMiddleName().compareTo(two.getMiddleName());
                } else {
                    return second;
                }
            } else {
                return first;
            }
        }
    }
}
