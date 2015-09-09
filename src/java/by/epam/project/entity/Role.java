package by.epam.project.entity;

import java.util.Objects;

/**
 * Class of user role objects.
 * @author Helena.Grouk
 */
public class Role {
    private Integer idRole;
    private String roleName;

    public Role(){}
    public Role(int idRole) {
        this.idRole = idRole;
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
        Role eq = (Role)obj;
        if (!super.equals(eq)){
            return false;
        }
        if(!Objects.equals(idRole, eq.idRole)) {
            return false;
        }
        return !(roleName == null ? eq.roleName != null : !roleName.equals(eq.roleName));
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idRole);
        hash = 23 * hash + Objects.hashCode(this.roleName);
        return hash;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(getClass().getName());
        str.append('@');
        str.append(", idRole: ");
        str.append(idRole);
        str.append(", roleName: ");
        str.append(roleName);
        return str.toString();
    }
    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }
    public Integer getIdRole(){
        return this.idRole;
    }
    public void setRoleName(String name){
        this.roleName = name;
    }
    public String getRoleName(){
        return this.roleName;
    }
}
