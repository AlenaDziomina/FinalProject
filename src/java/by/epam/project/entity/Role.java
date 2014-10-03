/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

/**
 *
 * @author User
 */
public final class Role {
    
    private Integer idRole;
    private String roleName;
    
    public Role(){}

    public Role(int idRole) {
        setIdRole(idRole);
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
